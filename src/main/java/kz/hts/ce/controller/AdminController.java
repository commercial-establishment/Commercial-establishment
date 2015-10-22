package kz.hts.ce.controller;

import kz.hts.ce.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/admins/{id}/lock")
    public String lockAdmin(@PathVariable long id) {
        adminService.lockById(id);
        return "redirect:";
    }

    @RequestMapping("/admins/{id}/reestablish")
    public String reestablishAdmin(@PathVariable long id) {
        adminService.reestablishById(id);
        return "redirect:";
    }
}
