package kz.hts.ce.controller;

import kz.hts.ce.entity.Admin;
import kz.hts.ce.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/admins/{id}")
    public String showAdminInformation(Model model, @PathVariable String id){
        Admin admin = adminService.findById(Long.valueOf(id));
        model.addAttribute("admin", admin);
        return "/admin-info";
    }
}
