package cn.coderzhx.blog2.web.admin;

import cn.coderzhx.blog2.po.Blog;
import cn.coderzhx.blog2.service.BlogService;
import cn.coderzhx.blog2.service.TagService;
import cn.coderzhx.blog2.service.TypeService;
import cn.coderzhx.blog2.vo.PageBean;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Calendar;

/**
 * @author zhx
 * @create 2019-10-19-16
 */
@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;
    @Value("${file.uploadfolder}")
    private String uploadfolder;
    //博客管理
    @GetMapping("/blogs")
    public String blogs(Model model, PageBean pageBean) {
        model.addAttribute("page",blogService.listBlog(pageBean));//带分页的所有文章
        model.addAttribute("types", typeService.listTypeAndCount(-1));//分类
        return "admin/blogs";
    }

    //后台搜索以及分页功能,为什么要分出来呢,其实这个类似于ajax异步刷新
    @PostMapping("/blogs/search")
    public String search(Model model, PageBean pageBean) {
        model.addAttribute("page",blogService.listBlog(pageBean));//带分页的所有文章
        return "admin/blogs :: blogList";
    }
    //跳转到添加文章界面
    @GetMapping("/blogs/input")
    public String input(Model model) {
        model.addAttribute("types", typeService.listTypeAndCount(-1));//分类
        model.addAttribute("tags", tagService.listTagCount());//标签
        model.addAttribute("blog", new Blog());//由于修改和添加共享一个页面
        return "admin/blogs-input";

    }
    //跳转到修改文章页面
    @GetMapping("/blogs/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Blog blog = blogService.getBlogById(id);//根据id得到文章
        blog.setTags(tagService.listTagByBlogId(blog.getId())); //根据id获取标签
        blog.setTagIds(blog.tagsToIds(blog.getTags())); //转化为,间隔的字符串
        model.addAttribute("types", typeService.listTypeAndCount(-1));//分类
        model.addAttribute("tags", tagService.listTagCount());//标签
        model.addAttribute("blog",blog);
        return "admin/blogs-input";
    }

    //点击添加或者修改文章
    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes) {


        if (blog.getId() == null) {//添加
            blogService.saveBlog(blog);
        } else {//修改
           blogService.updateBlog(blog);
        }
        attributes.addFlashAttribute("message", "操作成功");
        return "redirect:/admin/blogs";
    }
    //删除文章
    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes) {
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/blogs";
    }


    //上传图片
    @ResponseBody
    @PostMapping ("/uploadImg")
    public String  uploadImg(HttpServletRequest request, @RequestParam(value = "editormd-image-file", required = false) MultipartFile file){
        try {
            //需要上传的路径
            String rootPath = uploadfolder;
            //Calendar.getInstance()是获取一个Calendar对象并可以进行时间的计算，时区的指定
            Calendar date = Calendar.getInstance();
            //获得日期路径,MONTH个值的初始值是0，因此我们要用它来表示正确的月份时就需要加1。
            File dateFile = new File(date.get(Calendar.YEAR)+"/"+(date.get(Calendar.MONTH)+1)+"/"+(date.get(Calendar.DATE)));
            //获得文件名字
            String originalFile = file.getOriginalFilename();
            //得到完整路径名
            File newFile = new File(rootPath+ File.separator+dateFile+File.separator+originalFile);
            //文件不存在就创建
            if(!newFile.getParentFile().exists()){
                newFile.getParentFile().mkdirs();
            }
            file.transferTo(newFile);
            String url="/upload/"+date.get(Calendar.YEAR)+"/"+(date.get(Calendar.MONTH)+1)+"/"+date.get(Calendar.DATE)+"/"+file.getOriginalFilename();
            JSONObject obj = new JSONObject();
            obj.put("success",1);
            obj.put("message", "上传成功");
            obj.put("url", url);
           return obj.toString();
        } catch (Exception e) {
            JSONObject obj = new JSONObject();
            obj.put("success",0);
            obj.put("message", "上传失败");
            obj.put("url", "");
            return obj.toString();

        }
    }


}
