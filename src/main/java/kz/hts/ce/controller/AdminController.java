package kz.hts.ce.controller;

import kz.hts.ce.entity.Admin;
import kz.hts.ce.entity.Role;
import kz.hts.ce.service.AdminService;
import kz.hts.ce.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Date;

@Controller
public class AdminController {

    public static final String ADMIN = "ADMIN";

    @Autowired
    private AdminService adminService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/admins/{id}/lock")
    public String lock(@PathVariable long id) {
        Admin admin = adminService.findById(id);
        admin.setEndWorkDate(new Date());
        admin.setBlocked(true);
        adminService.save(admin);
        adminService.updateEndWorkDate(new Date(), id);
        adminService.lockById(id);
        return "redirect:";
    }

    @RequestMapping("/admins/{id}/reestablish")
    public String reestablish(@PathVariable long id) {
        adminService.updateStartAndEndWorkDate(new Date(), null, id);
        adminService.reestablishById(id);
        return "redirect:";
    }

    @RequestMapping(value = "/admins/{id}/edit", method = RequestMethod.POST)
    public String edit(@PathVariable long id, @Valid @ModelAttribute("admin") Admin admin, BindingResult result) {
        if (result.hasErrors()) {
            return "admin-edit";
        }
        Role role = roleService.findByName(ADMIN);
        admin.setId(id);
        admin.setRole(role);
        adminService.save(admin);
        return "redirect:";
    }

    @RequestMapping(value = "/admins/create-save", method = RequestMethod.POST)
    public String create(Model model, @ModelAttribute("admin") Admin admin) {
        Role role = roleService.findByName("ADMIN");

        admin.setRole(role);

        admin.setPassword(passwordEncoder.encode(admin.getPassword()));

        admin.setStartWorkDate(new Date());
        adminService.save(admin);
        return "redirect:";
    }
}
