package kz.hts.ce.controller.admin;

import kz.hts.ce.model.entity.Admin;
import kz.hts.ce.model.entity.Gender;
import kz.hts.ce.model.entity.Role;
import kz.hts.ce.service.AdminService;
import kz.hts.ce.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.UUID;

@Controller
public class AdminPageController {

    private static final String ADMIN = "ADMIN";
    private static final String GENDERS = "genders";
    private static final String ADMIN_LOWER_CASE = "admin";
    private static final String ADMINS = "admins";

    @Autowired
    private AdminService adminService;
    @Autowired
    private SpringUtil springUtil;

    @RequestMapping("/admin/admins/{id}")
    public String information(Model model, @PathVariable UUID id) {
        Admin admin = adminService.findById(id);
        model.addAttribute(ADMIN_LOWER_CASE, admin);
        return "/admin-info";
    }

    @RequestMapping("/admin/admins/{id}/edit")
    public String edit(Model model, @PathVariable UUID id) {
        Admin admin = adminService.findById(id);
        List<Gender> genders = springUtil.getGenders();
        List<Role> roles = springUtil.getRoles();

        model.addAttribute(GENDERS, genders);
        model.addAttribute("roles", roles);
        model.addAttribute(ADMIN_LOWER_CASE, admin);
        return "admin-edit";
    }

    @RequestMapping("/admin/admins/create")
    public String create(Model model) {
        Admin admin = new Admin();
        List<Gender> genders = springUtil.getGenders();

        model.addAttribute(GENDERS, genders);
        model.addAttribute(ADMIN_LOWER_CASE, admin);
        return "admin-create";
    }

    @RequestMapping(value = "/admin/admins",method = RequestMethod.GET)
    public String admins(Model model) {
        List<Admin> admins = adminService.findByRoleName(ADMIN);
        model.addAttribute(ADMINS, admins);
        return ADMINS;
    }
}
