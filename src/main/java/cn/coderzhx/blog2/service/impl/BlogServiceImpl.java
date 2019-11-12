package cn.coderzhx.blog2.service.impl;

import cn.coderzhx.blog2.mapper.BlogMapper;
import cn.coderzhx.blog2.mapper.TagMapper;
import cn.coderzhx.blog2.po.Blog;
import cn.coderzhx.blog2.service.BlogService;
import cn.coderzhx.blog2.util.BlogIndex;
import cn.coderzhx.blog2.vo.PageBean;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*S
 * @author zhx
 * @create 2019-10-16-18
 */
@Service
public class BlogServiceImpl implements BlogService {
    @Resource
    private BlogMapper blogMapper;
    @Resource
    private TagMapper tagMapper;

    private BlogIndex blogIndex = new BlogIndex();

    public  static HashMap<Long, Long> map=new HashMap<>();//文章id,访问次数
    @Override
    public PageBean listBlog(PageBean pageBean) {
        Long size = blogMapper.countBlog(pageBean);
        //Long转化为int,方法中定义为int也没事毕竟数据还很小
        PageBean pageBean1 = new PageBean(pageBean.getCurrentPage(), Math.toIntExact(size), pageBean.getPageSize());
        pageBean1.setPublished(pageBean.getPublished());//找出不为草稿的文章
        pageBean1.setLunbo(pageBean.getLunbo());//找出不为草稿的文章
        pageBean1.setTypeId(pageBean.getTypeId());
        pageBean1.setTitle(pageBean.getTitle());
        pageBean1.setList(blogMapper.listBlog(pageBean1));
        return pageBean1;
    }

    @Override
    @Cacheable(cacheNames = "findBlogById")
    public Blog getBlogById(Long id) {
        System.out.println("测试缓存");
        Blog blogById = blogMapper.getBlogById(id);
        return blogById;
    }

    @Override
    public List<Blog> listRecommendBlogTop(Integer size) {
        return blogMapper.listRecommendBlogTop(size);
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        //获取时间
        List<String> list = blogMapper.getYearAndMonth();
        //指定时间下的文章
        Map<String, List<Blog>> map = new HashMap<>();
        for (String l : list) {
            map.put(l, blogMapper.findByYear(l));
        }
        return map;
    }
    @Transactional
    @Override
    public void saveBlog(Blog blog) {
        blogMapper.saveBlog(blog);
        //添加到文章-标签表
        String[] tagsarr=blog.getTagIds().split(",");
        for (int i = 0; i < tagsarr.length; i++) {
            tagMapper.saveBlogTags(blog.getId(),Long.parseLong(tagsarr[i]));
        }
        //添加文章索引
        try {
            blogIndex.addIndex(blog);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Transactional
    @Override
    @CachePut(cacheNames = "findBlogById", key = "#blog.id")
    public void updateBlog(Blog blog) {
        //先删除所有该文章id的标签
        tagMapper.deleteTagByBlogId(blog.getId());
        //再添加
        String[] tagsarr=blog.getTagIds().split(",");
        for (int i = 0; i < tagsarr.length; i++) {
            tagMapper.saveBlogTags(blog.getId(),Long.parseLong(tagsarr[i]));
        }
        blogMapper.updateBlog(blog);
        //修改文章索引
        try {
            blogIndex.updateIndex(blog);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Blog> listLunboBlog() {
        return blogMapper.listLunboBlog();
    }

    @Override
    public synchronized  void addViews(Long id) {
        Long i= map.get(id);//获取出现次数
        if (i==null) {
            map.put(id, 1L);
        }else {
            map.put(id, i+1);
        }
    }

    @Override
    public HashMap<Long, Long> getNewBlogVisit() {
        return map;
    }

    @Override
    public void cleanMap() {
        map=new HashMap<>();
    }

    @Transactional
    @Override
    public void addViewsToDateBase() {
        //这里才是真正的定时任务添加到把总记录数据数据库
        for (Map.Entry<Long, Long> m : map.entrySet()) {
            //添加数据到数据库
            blogMapper.addViewsToDateBase(m.getKey(),m.getValue());
            //还要更新缓存中的文章的访问次数
            Blog blogById = getBlogById(m.getKey());
            blogById.setViews(m.getValue()+blogById.getViews());
            try {
                blogIndex.updateIndex(blogById);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        map.clear();
    }



    @Transactional
    @Override
    @CacheEvict(cacheNames = "findBlogById", key = "#id")
    public void deleteBlog(Long id) {
        //首先删除包含外键的记录
        //删除关联标签
        tagMapper.deleteTagByBlogId(id);
        blogMapper.deleteBlog(id);
        //删除文章索引
        try {
            blogIndex.deleteIndex(String.valueOf(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Long countBlog(PageBean pageBean) {
        return blogMapper.countBlog(pageBean);
    }

}