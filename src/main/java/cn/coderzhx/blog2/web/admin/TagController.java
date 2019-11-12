package cn.coderzhx.blog2.web.admin;

import cn.coderzhx.blog2.po.Tag;
import cn.coderzhx.blog2.service.TagService;
import cn.coderzhx.blog2.vo.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author zhx
 * @create 2019-10-25-13
 */
@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    private TagService tagService;

    //列出带分页的标签
    @GetMapping("/tags")
    public String tags(PageBean pageBean, Model model) {
        model.addAttribute("page", tagService.listTag(pageBean));
        return "admin/tags";
    }

    //跳转到添加标签页面
    @GetMapping("/tags/input")
    public String input(Model model) {
        model.addAttribute("tag", new Tag());
        return "admin/tags-input";
    }

    //跳转到修改标签页面
    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("tag", tagService.getTag(id));
        return "admin/tags-input";
    }

    //添加或者修改标签
    @PostMapping("/tags")
    public String post(Tag tag, BindingResult result, RedirectAttributes attributes) {
        if (tag.getId() == null) {//表示是添加
            Tag tag1 = tagService.getTagByName(tag.getName());
            if (tag1==null){
                tagService.saveTag(tag);
                attributes.addFlashAttribute("message", "新增成功");
            }else{
                result.rejectValue("name", "nameError", "标签重复!");
                return "admin/tags-input";
            }
        } else {//表示修改
            tagService.updateTag(tag);
            attributes.addFlashAttribute("message", "修改成功");
        }
        return "redirect:/admin/tags";
    }
    //删除标签
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/tags";
    }

}


