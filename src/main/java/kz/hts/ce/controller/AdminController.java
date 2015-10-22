package kz.hts.ce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

    @RequestMapping("/admins/{id}")
    public String showAdminInformation(@PathVariable String id){
        System.out.println(id);
        return "/admin-info";
    }
}
