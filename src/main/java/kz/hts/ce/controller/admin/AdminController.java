package kz.hts.ce.controller.admin;

import kz.hts.ce.entity.Admin;
import kz.hts.ce.entity.Gender;
import kz.hts.ce.entity.Provider;
import kz.hts.ce.entity.Role;
import kz.hts.ce.service.AdminService;
import kz.hts.ce.service.GenderService;
import kz.hts.ce.service.ProviderService;
import kz.hts.ce.service.RoleService;
import kz.hts.ce.util.SpringUtil;
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
import java.util.UUID;

@Controller
public class AdminController {

    private static final String ADMIN = "ADMIN";
    private static final String REDIRECT = "redirect:";
    private static final String GENDERS = "genders";
    private static final String ROLES = "roles";

    @Autowired
    private AdminService adminService;
    @Autowired
    private ProviderService providerService;

    @Autowired
    private SpringUtil springUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/admin/admins/{id}/lock")
    public String lock(@PathVariable UUID id) {
        Admin admin = adminService.findById(id);
        admin.setEndWorkDate(new Date());
        admin.setBlocked(true);
        adminService.save(admin);
        adminService.updateEndWorkDate(new Date(), id);
        adminService.lockById(id);
        return REDIRECT;
    }

    @RequestMapping("/admin/admins/{id}/reestablish")
    public String reestablish(@PathVariable UUID id) {
        adminService.updateStartAndEndWorkDate(new Date(), null, id);
        adminService.reestablishById(id);
        return REDIRECT;
    }

    @RequestMapping(value = "/admin/admins/{id}/edit", method = RequestMethod.POST)
    public String edit(Model model, @PathVariable UUID id, @Valid @ModelAttribute("admin") Admin admin, BindingResult result) {
        Role role = SpringUtil.roleMap.get(ADMIN);
        admin.setRole(role);

        if (result.hasErrors()) {
            List<Gender> genders = springUtil.getGenders();
            List<Role> roles = springUtil.getRoles();

            model.addAttribute(GENDERS, genders);
            model.addAttribute(ROLES, roles);
            return "admin-edit";
        }

        admin.setId(id);
        adminService.save(admin);
        return REDIRECT;
    }

    @RequestMapping(value = "/admin/admins/create-save", method = RequestMethod.POST)
    public String create(Model model, @Valid @ModelAttribute("admin") Admin admin, BindingResult result) {
        List<Gender> genders = springUtil.getGenders();
        if (result.hasErrors()) {
            model.addAttribute(GENDERS, genders);
            return "admin-create";
        }

        Admin adminFromDB = adminService.findByUsernameAndBlocked(admin.getUsername(), false);
        Provider providerFromDB = providerService.findByUsernameAndBlocked(admin.getUsername(), false);
        if (adminFromDB == null && providerFromDB == null) {
            Role role = SpringUtil.roleMap.get(ADMIN);
            admin.setRole(role);
            admin.setPassword(passwordEncoder.encode(admin.getPassword()));
            admin.setStartWorkDate(new Date());
            adminService.save(admin);
        } else {
            model.addAttribute("loginIsOccupied", "Указанный логин уже занят");
            model.addAttribute(GENDERS, genders);
        }
        return REDIRECT;
    }
}
