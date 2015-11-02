package kz.hts.ce.controller.shop;

import kz.hts.ce.entity.City;
import kz.hts.ce.entity.Shop;
import kz.hts.ce.entity.Type;
import kz.hts.ce.service.CityService;
import kz.hts.ce.service.ShopService;
import kz.hts.ce.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ShopPageController {

    @Autowired
    private ShopService shopService;
    @Autowired
    private CityService cityService;
    @Autowired
    private TypeService typeService;


    @RequestMapping("/shops/{id}")
    public String providerInformation(Model model, @PathVariable long id){
        Shop shop = shopService.findById(id);
        model.addAttribute("shop", shop);
        return "/shop-info";
    }

    @RequestMapping("/shops/{id}/edit")
    public String edit(Model model, @PathVariable long id){
        List<City> cities = cityService.findAll();
        List<Type> types = typeService.findAll();
        Shop shop = shopService.findById(id);

        model.addAttribute("types", types);
        model.addAttribute("shop", shop);
        model.addAttribute("cities", cities);
        return "shop-edit";
    }

    @RequestMapping("/shops/create")
    public String create(Model model){
        Shop shop = new Shop();
        List<City> cities = cityService.findAll();
        List<Type> types = typeService.findAll();

        model.addAttribute("types", types);
        model.addAttribute("cities", cities);
        model.addAttribute("shop", shop);
        return "shop-create";
    }

    @RequestMapping(value = "/shops",method = RequestMethod.GET)
    public String shopsPage(Model model) {
        List<Shop> shops = shopService.findAll();
        model.addAttribute("shops", shops);
        return "shops";
    }

}
