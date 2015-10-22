package kz.hts.ce.controller;

import kz.hts.ce.entity.Admin;
import kz.hts.ce.entity.Shop;
import kz.hts.ce.service.AdminService;
import kz.hts.ce.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import static kz.hts.ce.util.SpringUtils.getPrincipal;

@Controller
public class TransitionPageController {

    public static final String ADMIN = "ADMIN";
    @Autowired
    private AdminService adminService;
    @Autowired
    private ShopService shopService;

    /*ADMIN*/
    @RequestMapping("/admins/{id}")
    public String adminInformationPage(Model model, @PathVariable long id) {
        Admin admin = adminService.findById(id);
        model.addAttribute("admin", admin);
        return "/admin-info";
    }

    @RequestMapping(value = {"/","/admin"}, method = RequestMethod.GET)
    public String adminPage(Model model) {
        String username = getPrincipal();
        Admin admin = adminService.findByUsername(username);
        model.addAttribute("name", admin.getName());
        model.addAttribute("patronymic", admin.getPatronymic());
        return "admin";
    }

    @RequestMapping("/admins/{id}/edit")
    public String editAdminPage(@PathVariable long id) {
        adminService.reestablishById(id);
        return "redirect:";
    }

    @RequestMapping(value = "/admins",method = RequestMethod.GET)
    public String adminsPage(Model model) {
        List<Admin> admins = adminService.findByRoleName(ADMIN);
        model.addAttribute("admins", admins);
        return "admins";
    }

    @RequestMapping(value = "/recovery", method = RequestMethod.GET)
    public String passwordRecoveryPage() {
        return "password-recovery";
    }

    /*LOGIN*/
    @RequestMapping(value = "/login/form",method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    /*PROVIDER*/
    @RequestMapping(value = "/providers",method = RequestMethod.GET)
    public String providersPage(Model model) {
        return "providers";
    }

    /*SHOP*/
    @RequestMapping(value = "/shops",method = RequestMethod.GET)
    public String shopsPage(Model model) {
        List<Shop> shops = shopService.findAll();
        model.addAttribute("shops", shops);
        return "shops";
    }

    @RequestMapping("/shops/{id}")
    public String shopInformationPage(Model model, @PathVariable String id){
        Shop shop = shopService.findById(Long.valueOf(id));
        model.addAttribute("shop", shop);
        return "/shop-info";
    }
}
