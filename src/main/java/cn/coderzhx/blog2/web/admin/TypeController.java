package cn.coderzhx.blog2.web.admin;

import cn.coderzhx.blog2.po.Type;
import cn.coderzhx.blog2.service.TypeService;
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
 * @create 2019-10-19-16
 */
@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    private TypeService typeService;

    //列出带分页的标签
    @GetMapping("/types")
    public String types(PageBean pageBean, Model model) {
        model.addAttribute("page", typeService.listType(pageBean));
        return "admin/types";
    }

    //跳转到添加标签页面
    @GetMapping("/types/input")
    public String input(Model model) {
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }

    //跳转到修改标签页面
    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("type", typeService.getType(id));
        return "admin/types-input";
    }

    //添加或者修改标签
    @PostMapping("/types")
    public String post(Type type, BindingResult result, RedirectAttributes attributes) {
        if (type.getId() == null) {//表示是添加
            Type type1 = typeService.getTypeByName(type.getName());
            if (type1==null){
                typeService.saveType(type);
                attributes.addFlashAttribute("message", "新增成功");
            }else{
                result.rejectValue("name", "nameError", "标签重复!");
                return "admin/types-input";
            }
        } else {//表示修改
            typeService.updateType(type);
            attributes.addFlashAttribute("message", "修改成功");
        }
        return "redirect:/admin/types";
    }
    //删除标签
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        typeService.deleteType(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/types";
    }
}
