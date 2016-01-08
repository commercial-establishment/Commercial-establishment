package kz.hts.ce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginPageController {

    private static final String SPRING_SECURITY_LAST_EXCEPTION = "SPRING_SECURITY_LAST_EXCEPTION";

    @RequestMapping(value = "/login/form", method = RequestMethod.GET)
    public String loginPage(HttpServletRequest request, Model model) {
        Exception exception = (Exception) request.getSession().getAttribute(SPRING_SECURITY_LAST_EXCEPTION);
        if (exception != null) {
            model.addAttribute("error", "incorrect.username.or.password");
        }
        return "login";
    }
}
