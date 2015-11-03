package kz.hts.ce.controller.admin;

import kz.hts.ce.entity.Admin;
import kz.hts.ce.entity.Gender;
import kz.hts.ce.entity.Role;
import kz.hts.ce.service.AdminService;
import kz.hts.ce.service.GenderService;
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
import java.util.List;

@Controller
public class AdminController {

    public static final String ADMIN = "ADMIN";

    @Autowired
    private AdminService adminService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private GenderService genderService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/admin/admins/{id}/lock")
    public String lock(@PathVariable long id) {
        Admin admin = adminService.findById(id);
        admin.setEndWorkDate(new Date());
        admin.setBlocked(true);
        adminService.save(admin);
        adminService.updateEndWorkDate(new Date(), id);
        adminService.lockById(id);
        return "redirect:";
    }

    @RequestMapping("/admin/admins/{id}/reestablish")
    public String reestablish(@PathVariable long id) {
        adminService.updateStartAndEndWorkDate(new Date(), null, id);
        adminService.reestablishById(id);
        return "redirect:";
    }

    @RequestMapping(value = "/admin/admins/{id}/edit", method = RequestMethod.POST)
    public String edit(Model model, @PathVariable long id, @Valid @ModelAttribute("admin") Admin admin, BindingResult result) {
        Role role = roleService.findByName(ADMIN);
        admin.setRole(role);

        if (result.hasErrors()) {
            List<Gender> genders = genderService.findAll();
            List<Role> roles = roleService.findAll();

            model.addAttribute("genders", genders);
            model.addAttribute("roles", roles);
            return "admin-edit";
        }

        admin.setId(id);
        adminService.save(admin);
        return "redirect:";
    }

    @RequestMapping(value = "/admin/admins/create-save", method = RequestMethod.POST)
    public String create(Model model, @Valid @ModelAttribute("admin") Admin admin, BindingResult result) {
        if (result.hasErrors()) {
            return "admin-edit";
        }
        Role role = roleService.findByName("ADMIN");

        admin.setRole(role);

        admin.setPassword(passwordEncoder.encode(admin.getPassword()));

        admin.setStartWorkDate(new Date());
        adminService.save(admin);
        return "redirect:";
    }
}
