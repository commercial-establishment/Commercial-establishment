package kz.hts.ce.controller;

import kz.hts.ce.entity.Admin;
import kz.hts.ce.entity.Provider;
import kz.hts.ce.service.AdminService;
import kz.hts.ce.service.ProviderService;
import kz.hts.ce.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

import static kz.hts.ce.util.SpringUtils.getPrincipal;

@Controller
public class TransitionPageController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private ProviderService providerService;

    @RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
    public String admin(Model model, HttpServletRequest request) {
//        String username = getPrincipal();
//        if (request.isUserInRole("ROLE_ADMIN")) {
//            Admin admin = adminService.findByUsername(username);
//        } else if (request.isUserInRole("ROLE_PROVIDER")) {
//            Provider provider = providerService.findByUsername(username);
//            provider.ge
//        }
//        Admin admin = adminService.findByUsername(username);
//        model.addAttribute("name", admin.getName());
//        model.addAttribute("patronymic", admin.getPatronymic());
        return "home";
    }

    /*SHOP*/

//
//    @RequestMapping("/shops/{id}")
//    public String shopInformationPage(Model model, @PathVariable String id){
//        Shop shop = shopService.findById(Long.valueOf(id));
//        model.addAttribute("shop", shop);
//        return "/shop-info";
//    }

    @RequestMapping(value = "/recovery", method = RequestMethod.GET)
    public String passwordRecovery() {
        return "password-recovery";
    }
}
