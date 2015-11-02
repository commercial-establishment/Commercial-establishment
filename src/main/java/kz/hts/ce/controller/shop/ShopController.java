package kz.hts.ce.controller.shop;

import kz.hts.ce.entity.City;
import kz.hts.ce.entity.Shop;
import kz.hts.ce.entity.Type;
import kz.hts.ce.service.CityService;
import kz.hts.ce.service.RoleService;
import kz.hts.ce.service.ShopService;
import kz.hts.ce.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ShopController {

    @Autowired
    private ShopService shopService;
    @Autowired
    private CityService cityService;
    @Autowired
    private TypeService typeService;

    @RequestMapping(value = "/shops/{id}/lock")
    public String lock(@PathVariable long id) {
        Shop shop = shopService.findById(id);
        shop.setBlocked(true);
        shopService.save(shop);
        shopService.lockById(id);
        return "redirect:";
    }

    @RequestMapping("/shops/{id}/reestablish")
    public String reestablish(@PathVariable long id) {
        shopService.reestablishById(id);
        return "redirect:";
    }

    @RequestMapping(value = "/shops/{id}/edit", method = RequestMethod.POST)
    public String edit(Model model, @PathVariable long id, @Valid @ModelAttribute("shop") Shop shop, BindingResult result) {
        if (result.hasErrors()) {
            List<City> cities = cityService.findAll();
            List<Type> types = typeService.findAll();
            model.addAttribute("types", types);
            model.addAttribute("cities", cities);
            return "shop-edit";
        }

        shop.setId(id);
        shopService.save(shop);
        return "redirect:";
    }

    @RequestMapping(value = "/shops/create-save", method = RequestMethod.POST)
    public String create(Model model, @Valid @ModelAttribute("shop") Shop shop, BindingResult result) {
        if (result.hasErrors()) {
            return "shop-create";
        }
        shopService.save(shop);
        return "redirect:";
    }
}
