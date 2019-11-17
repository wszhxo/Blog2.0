package cn.coderzhx.blog2.web.admin;

import cn.coderzhx.blog2.aspect.LogAspect;
import cn.coderzhx.blog2.po.User;
import cn.coderzhx.blog2.service.BlogService;
import cn.coderzhx.blog2.service.IpService;
import cn.coderzhx.blog2.service.UserService;
import cn.coderzhx.blog2.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private IpService ipService;
    @Autowired
    private BlogService blogService;

    //跳转登录界面
    @GetMapping(value = {"", "login"})
    public String loginPage() {
        return "admin/login";
    }

    //登录
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes, Model model) {

        User user = userService.checkUser(username);
        if (user == null) {
            //用户不存在
            attributes.addFlashAttribute("message", "用户不存在!");
            return "redirect:/admin";
        } else if (!user.getPassword().equals(MD5Utils.code(password))) {
            //密码错误
            attributes.addFlashAttribute("message", "密码错误!");
            return "redirect:/admin";
        } else {
            //正确
            user.setPassword(null);
            session.setAttribute("user", user);

            //网站的一些统计信息
           //新增访客
            session.setAttribute("newVisits", LogAspect.newmap) ;
            //之后设为空,类似于消息看过之后,就不会再提示了,就清空了
            LogAspect.newmap=new HashMap<>();
            //新增文章访问量
            HashMap<Long, Long> map=blogService.getNewBlogVisit();
            session.setAttribute("blogVisit", map)  ;
            blogService.cleanMap();
            return "admin/index";
        }
    }

    //注销
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/admin";
    }

    //主页
    @GetMapping("/index")
    public String index(HttpSession session) {
        User user = (User) session.getAttribute("user");
        user.setPassword(null);
        session.setAttribute("user", user);
        return "admin/index";
    }
}
