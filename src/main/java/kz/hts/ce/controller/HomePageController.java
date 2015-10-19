package kz.hts.ce.controller;

import kz.hts.ce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomePageController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public String goToLoginPage(ModelMap model) {
        model.addAttribute("message", "Hello Spring MVC Framework!");
        return "index";
    }
}
