package kz.hts.ce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginPageController {

    @RequestMapping(value = "/login/form",method = RequestMethod.GET)
    public String loginPage(HttpServletRequest request, Model model, String logout) {
        return "login";
    }
}
