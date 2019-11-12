package cn.coderzhx.blog2.web;

import cn.coderzhx.blog2.aspect.LogAspect;
import cn.coderzhx.blog2.po.Blog;
import cn.coderzhx.blog2.po.Tag;
import cn.coderzhx.blog2.po.Type;
import cn.coderzhx.blog2.service.*;
import cn.coderzhx.blog2.util.BlogIndex;
import cn.coderzhx.blog2.vo.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static cn.coderzhx.blog2.util.MarkdownUtils.markdownToHtmlExtensions;

/**
 * @author zhx
 * @create 2019-10-13-16
 */
@Controller
public class IndexController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;
    @Autowired
    private UserService userService;
    @Autowired
    private LinkService linkService;
    static BlogIndex index=new BlogIndex();
    static PageBean listBlogs;

    //主页,或者点击上一页,下一页
    @GetMapping("/")
    public String index(Model model,PageBean pageBean) {
        pageBean.setPublished(0);//设置不为草稿标志
        pageBean.setLunbo(0);//找出不是轮播的
        model.addAttribute("page",blogService.listBlog(pageBean));//带分页的所有文章
        model.addAttribute("types", typeService.listTypeAndCount(6));//分类
        model.addAttribute("tags", tagService.listTagAndCount(10));//标签
        model.addAttribute("recommendBlogs", blogService.listRecommendBlogTop(6));//推荐文章
        model.addAttribute("lunboBlogs", blogService.listLunboBlog());//轮播文章
        model.addAttribute("link", linkService.listLink());//友情链接
        model.addAttribute("ipConut", LogAspect.getVisitCount());//ip和总浏览量
        return "index";
    }

    //全文搜索
    @GetMapping("/search")
    public String search(@RequestParam String query, Model model) throws Exception {
         listBlogs = index.searchBlog(query);
        System.out.println(listBlogs.toString());
        model.addAttribute("page", listBlogs);
        return "search";
    }
    //全文搜索的分页
    @GetMapping("/search/{page}")
    public String searchNext(@RequestParam String query,@PathVariable("page" ) Integer page, Model model) throws Exception {
        listBlogs.setCurrentPage(page);
        model.addAttribute("page", listBlogs);
        return "search";
    }
    //根据id列出文章
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model) {
        blogService.addViews(id);
        Blog blogById = blogService.getBlogById(id);
        blogById.setContent(markdownToHtmlExtensions(blogById.getContent()));
        model.addAttribute("blog", blogById);
        return "blog";
    }
    //找到对应id的分类
    @GetMapping("/types/{id}")
    public String types(@PathVariable Integer id, Model model,PageBean pageBean) {
        List<Type> types = typeService.listTypeAndCount(-1);//-1列出所有分类
        model.addAttribute("types", types);
        if (id==-1){//表示点击分类导航
            //列出所有的文章
            PageBean pageBean1 = blogService.listBlog(pageBean);
            pageBean1.setTypeId(id);
            model.addAttribute("page",pageBean1);//带分页的所有文章
        }else{//表示点击某一个分类
            pageBean.setTypeId(id);//用于分页
            model.addAttribute("page", typeService.listTypeByid(pageBean));
        }
        model.addAttribute("activeTypeId", id);//记录当前分类号
        return "types";
    }

    //找到对应id的标签
    @GetMapping("/tags/{id}")
    public String tags(@PathVariable Integer id, Model model,PageBean pageBean) {
        List<Tag> tags = tagService.listTagAndCount(-1);//-1列出所有标签
        model.addAttribute("tags", tags);
        if (id==-1){//表示点击标签导航
            //列出所有的文章
//            PageBean pageBean1 = blogService.listBlog(pageBean);
//            pageBean1.setTagId(id);
            model.addAttribute("page",pageBean);//带分页的所有文章
        }else{//表示点击某一个分类
            pageBean.setTagId(id);//用于分页
            model.addAttribute("page", tagService.listTagByid(pageBean));
        }
        model.addAttribute("activeTagId", id);//记录当前分类号
        return "tags";
    }
    //归档
    @GetMapping("/archives")
    public String archives(Model model,PageBean pageBean) {
        model.addAttribute("archiveMap", blogService.archiveBlog());
        model.addAttribute("blogCount", blogService.countBlog(pageBean));
        return "archives";

    }
    //关于我
    @GetMapping("/about")
    public String about(Model model) {

        model.addAttribute("config", userService.aboutme());
        return "about";
    }






}
