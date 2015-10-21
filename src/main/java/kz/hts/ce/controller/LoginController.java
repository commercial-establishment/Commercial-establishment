package kz.hts.ce.controller;

import kz.hts.ce.entity.Admin;
import kz.hts.ce.entity.User;
import kz.hts.ce.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/form",method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "/form/recovery",method = RequestMethod.GET)
    public String reestablishPasswordPage() {
        return "password-recovery";
    }

    @RequestMapping(value = "/form",method = RequestMethod.POST)
    public String login(@ModelAttribute("username") String username, @ModelAttribute("password") String password) {
        Admin user = adminService.findByUsername(username);
        System.out.println(user);
        return "admin";
    }
}
