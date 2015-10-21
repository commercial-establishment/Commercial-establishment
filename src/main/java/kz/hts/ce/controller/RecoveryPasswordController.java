package kz.hts.ce.controller;

import kz.hts.ce.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RecoveryPasswordController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/recovery", method = RequestMethod.GET)
    public String passwordRecoveryPage() {
        return "password-recovery";
    }

    @RequestMapping(value = "/recovery", method = RequestMethod.POST)
    public String passwordRecovery(String newPassword, String oldPassword, String confirmPassword) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
//        adminService.updatePasswordById(newPassword, );
        return "login/form";
    }
}
