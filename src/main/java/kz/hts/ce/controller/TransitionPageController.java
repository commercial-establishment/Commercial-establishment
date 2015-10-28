package kz.hts.ce.controller;

import kz.hts.ce.entity.Shop;
import kz.hts.ce.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class TransitionPageController {

    @Autowired
    private ShopService shopService;

    /*LOGIN*/
    @RequestMapping(value = "/login/form",method = RequestMethod.GET)
    public String loginPage() {
        return "login";
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
