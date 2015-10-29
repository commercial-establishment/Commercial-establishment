package kz.hts.ce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginPageController {

    @RequestMapping(value = "/login/form",method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }
}
