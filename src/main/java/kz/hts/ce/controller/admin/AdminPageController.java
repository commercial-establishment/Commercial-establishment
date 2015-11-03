package kz.hts.ce.controller.admin;

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

@Controller
public class AdminPageController {

    public static final String ADMIN = "ADMIN";

    @Autowired
    private AdminService adminService;
    @Autowired
    private GenderService genderService;
    @Autowired
    private RoleService roleService;

    @RequestMapping("/admin/admins/{id}")
    public String information(Model model, @PathVariable long id) {
        Admin admin = adminService.findById(id);
        model.addAttribute("admin", admin);
        return "/admin-info";
    }



    @RequestMapping("/admin/admins/{id}/edit")
    public String edit(Model model, @PathVariable long id) {
        Admin admin = adminService.findById(id);
        List<Gender> genders = genderService.findAll();
        List<Role> roles = roleService.findAll();

        model.addAttribute("genders", genders);
        model.addAttribute("roles", roles);
        model.addAttribute("admin", admin);
        return "admin-edit";
    }

    @RequestMapping("/admin/admins/create")
    public String create(Model model) {
        Admin admin = new Admin();
        List<Gender> genders = genderService.findAll();

        model.addAttribute("genders", genders);
        model.addAttribute("admin", admin);
        return "admin-create";
    }

    @RequestMapping(value = "/admin/admins",method = RequestMethod.GET)
    public String admins(Model model) {
        List<Admin> admins = adminService.findByRoleName(ADMIN);
        model.addAttribute("admins", admins);
        return "admins";
    }
}
