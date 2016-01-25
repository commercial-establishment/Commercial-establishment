package kz.hts.ce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class InfoPageController {

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String info() {
        return "info";
    }
}
