package kz.hts.ce.controller;

import kz.hts.ce.entity.Admin;
import kz.hts.ce.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import static kz.hts.ce.util.SpringUtils.getPrincipal;

@Controller
public class TransitionPageController {

    public static final String ADMIN = "ADMIN";
    @Autowired
    private AdminService adminService;

    @RequestMapping(value = {"/","/admin"}, method = RequestMethod.GET)
    public String adminPage(Model model) {
        String username = getPrincipal();
        Admin admin = adminService.findByUsername(username);
        model.addAttribute("name", admin.getName());
        model.addAttribute("patronymic", admin.getPatronymic());
        return "admin";
    }

    @RequestMapping(value = "/login/form",method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "/admins",method = RequestMethod.GET)
    public String adminsPage(Model model) {
        List<Admin> admins = adminService.findByRoleName(ADMIN);
        model.addAttribute("admins", admins);
        return "admins";
    }

    @RequestMapping(value = "/providers",method = RequestMethod.GET)
    public String providersPage(Model model) {
        return "providers";
    }

    @RequestMapping(value = "/shops",method = RequestMethod.GET)
    public String shopsPage(Model model) {
        return "shops";
    }

    @RequestMapping(value = "/recovery", method = RequestMethod.GET)
    public String passwordRecoveryPage() {
        return "password-recovery";
    }
}
