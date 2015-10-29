package kz.hts.ce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ExceptionController {

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDeniedPage() {
        return "403";
    }
}
