package kz.hts.ce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomePageController {

    @RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
    public String admin(Model model, HttpServletRequest request) {
        return "home";
    }
}
