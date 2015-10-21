package kz.hts.ce.controller;

import kz.hts.ce.entity.Admin;
import kz.hts.ce.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static kz.hts.ce.util.SpringUtils.getPrincipal;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/admin",method = RequestMethod.GET)
    public String adminPage(Model model) {
        String username = getPrincipal();
        Admin admin = adminService.findByUsername(username);
        model.addAttribute("name", admin.getName());
        model.addAttribute("patronymic", admin.getPatronymic());
        return "admin";
    }
}
