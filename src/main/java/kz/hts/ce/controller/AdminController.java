package kz.hts.ce.controller;

import kz.hts.ce.entity.Admin;
import kz.hts.ce.entity.Role;
import kz.hts.ce.service.AdminService;
import kz.hts.ce.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private RoleService roleService;

    @Autowired
    private AdminPageController adminPageController;

    @RequestMapping(value = "/admins/{id}/lock")
    public String lock(@PathVariable long id) {
        adminService.lockById(id);
        return "redirect:";
    }

    @RequestMapping("/admins/{id}/reestablish")
    public String reestablish(@PathVariable long id) {
        adminService.reestablishById(id);
        return "redirect:";
    }

    @RequestMapping(value = "/admins/{id}/edit-save", method = RequestMethod.POST)
    public String edit(Model model, @PathVariable long id, @ModelAttribute("admin") Admin admin) {
        Role role = roleService.findByName("ADMIN");

        admin.setId(id);
        admin.setRole(role);

        adminService.save(admin);
        return adminPageController.adminInformationPage(model, id);
    }
}
