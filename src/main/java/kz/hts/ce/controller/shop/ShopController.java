package kz.hts.ce.controller.shop;

import kz.hts.ce.model.entity.*;
import kz.hts.ce.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class ShopController {

    private static final String REDIRECT = "redirect:";

    @Autowired
    private ShopService shopService;
    @Autowired
    private CityService cityService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private AreaService areaService;
    @Autowired
    private ShopProviderService shopProviderService;

    @RequestMapping(value = "/admin/shops/{id}/lock")
    public String lock(@PathVariable UUID id) {
        Shop shop = shopService.findById(id);
        shop.setBlocked(true);
        shopService.save(shop);
        shopService.lockById(id);
        return REDIRECT;
    }

    @RequestMapping("/admin/shops/{id}/reestablish")
    public String reestablish(@PathVariable UUID id) {
        shopService.reestablishById(id);
        return REDIRECT;
    }

    @RequestMapping(value = "/admin/shops/{id}/edit", method = RequestMethod.POST)
    public String edit(Model model, @PathVariable UUID id, @Valid @ModelAttribute("shop") Shop shop, BindingResult result) {
        if (result.hasErrors()) {
            List<City> cities = cityService.findAll();
            List<Type> types = typeService.findAll();
            model.addAttribute("types", types);
            model.addAttribute("cities", cities);
            return "shop-edit";
        }
        if (shop.getArea() == null) {
            Shop shopFromDB = shopService.findById(id);
            shop.setArea(shopFromDB.getArea());
        }
        shop.setId(id);
        shopService.save(shop);
        return REDIRECT;
    }

    @RequestMapping(value = "/admin/shops/create-save", method = RequestMethod.POST)
    public String create(Model model, @Valid @ModelAttribute("shop") Shop shop, BindingResult result) {
        if (result.hasErrors()) {
            List<City> cities = cityService.findAll();
            List<Type> types = typeService.findAll();
            model.addAttribute("cities", cities);
            model.addAttribute("types", types);
            return "shop-create";
        }
        shopService.save(shop);
        return REDIRECT;
    }

    @RequestMapping(value = "/admin/shops/areas", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<String> getAreasByCity(@RequestParam String city) {
        UUID cityId = cityService.findByName(city).getId();
        List<Area> areas = areaService.findByCityId(cityId);
        List<String> areaNames = new ArrayList<>();
        for (Area area : areas) areaNames.add(area.getName());
        return areaNames;
    }

    @RequestMapping(value = "/employees11", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)/*TODO remove*/
    @ResponseBody
    public String getForClient(Model model) {
        List<City> cities = cityService.findAll();
        model.addAttribute("cities", cities);
        return cities.get(0).getName();
    }

    @RequestMapping(value = "/json/shop-providers/add", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)/*TODO remove*/
    @ResponseBody
    public List<ShopProvider> getShopProvidersFromClient(@RequestBody List<ShopProvider> shopProviders) {
        for (ShopProvider shopProvider : shopProviders) shopProviderService.save(shopProvider);
        return shopProviders;
    }
}
