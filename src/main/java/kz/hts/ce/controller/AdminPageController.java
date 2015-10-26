package kz.hts.ce.controller;

import kz.hts.ce.entity.Admin;
import kz.hts.ce.entity.Gender;
import kz.hts.ce.entity.Role;
import kz.hts.ce.service.AdminService;
import kz.hts.ce.service.GenderService;
import kz.hts.ce.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import static kz.hts.ce.util.SpringUtils.getPrincipal;

@Controller
public class AdminPageController {

    public static final String ADMIN = "ADMIN";

    @Autowired
    private AdminService adminService;
    @Autowired
    private GenderService genderService;
    @Autowired
    private RoleService roleService;

    @RequestMapping("/admins/{id}")
    public String adminInformationPage(Model model, @PathVariable long id) {
        Admin admin = adminService.findById(id);
        model.addAttribute("admin", admin);
        return "/admin-info";
    }

    @RequestMapping(value = {"/","/admin"}, method = RequestMethod.GET)
    public String adminPage(Model model) {
        String username = getPrincipal();
        Admin admin = adminService.findByUsername(username);
        model.addAttribute("name", admin.getName());
        model.addAttribute("patronymic", admin.getPatronymic());
        return "admin";
    }

    @RequestMapping("/admins/{id}/edit")
    public String editAdminPage(Model model, @PathVariable long id) {
        Admin admin = adminService.findById(id);
        List<Gender> genders = genderService.findAll();
        List<Role> roles = roleService.findAll();

        model.addAttribute("genders", genders);
        model.addAttribute("roles", roles);
        model.addAttribute("admin", admin);
        return "admin-edit";
    }

    @RequestMapping(value = "/admins",method = RequestMethod.GET)
    public String adminsPage(Model model) {
        List<Admin> admins = adminService.findByRoleName(ADMIN);
        model.addAttribute("admins", admins);
        return "admins";
    }

    @RequestMapping(value = "/recovery", method = RequestMethod.GET)
    public String passwordRecoveryPage() {
        return "password-recovery";
    }
}
