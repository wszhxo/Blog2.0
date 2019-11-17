package cn.coderzhx.blog2.service;

import cn.coderzhx.blog2.po.Blog;
import cn.coderzhx.blog2.vo.PageBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhx
 * @create 2019-10-13-20
 */
public interface BlogService {
    //列出所有文章,实现了分页功能
    PageBean listBlog(PageBean pageBean);

    List<Blog> listRecommendBlogTop(Integer size);

    Map<String,List<Blog>> archiveBlog();

    Blog getBlogById(Long id);
    void saveBlog(Blog blog);
    void deleteBlog(Long id);
    Long countBlog(PageBean pageBean);
    Blog updateBlog(Blog blog);

    List<Blog> listLunboBlog();

    void addViews(Long id);
    void addViewsToDateBase();

    HashMap<Long, Long> getNewBlogVisit();

    void cleanMap();
}
