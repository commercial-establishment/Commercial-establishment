package kz.hts.ce.controller;

import kz.hts.ce.entity.Admin;
import kz.hts.ce.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

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

    @RequestMapping(value = "/admins/{id}/edit-save",method = RequestMethod.POST)
    public String edit(@PathVariable long id, @ModelAttribute("admin") Admin admin) {
        System.out.println(id);
        System.out.println(admin);
        return "azazaz";
    }
}
