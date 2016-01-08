package kz.hts.ce.controller.provider;

import kz.hts.ce.entity.*;
import kz.hts.ce.service.*;
import kz.hts.ce.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class ProviderController {

    private static final String PROVIDER = "PROVIDER";
    private static final String REDIRECT = "redirect:";
    private static final String ROLES = "roles";
    private static final String CITIES = "cities";

    @Autowired
    private ProviderService providerService;
    @Autowired
    private CityService cityService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductProviderService productProviderService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private ShopProviderService shopProviderService;
    @Autowired
    private AdminService adminService;

    @Autowired
    private SpringUtil springUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/admin/providers/{id}/lock")
    public String lock(@PathVariable UUID id) {
        Provider provider = providerService.findById(id);
        provider.setEndWorkDate(new Date());
        provider.setBlocked(true);
        providerService.save(provider);
        providerService.updateEndWorkDate(new Date(), id);
        providerService.lockById(id);
        return REDIRECT;
    }

    @RequestMapping("/admin/providers/{id}/reestablish")
    public String reestablish(@PathVariable UUID id) {
        providerService.updateStartAndEndWorkDate(new Date(), null, id);
        providerService.reestablishById(id);
        return REDIRECT;
    }

    @RequestMapping(value = "/admin/providers/create", method = RequestMethod.POST)
    public String create(Model model, @Valid @ModelAttribute("provider") Provider provider, BindingResult result) {
        List<City> cities = cityService.findAll();
        if (result.hasErrors()) {
            model.addAttribute(CITIES, cities);
            return "provider-create";
        }

        Admin adminFromDB = adminService.findByUsernameAndBlocked(provider.getUsername(), false);
        Provider providerFromDB = providerService.findByUsernameAndBlocked(provider.getUsername(), false);
        if (adminFromDB == null && providerFromDB == null) {
            Role role = springUtil.getRoleMap().get(PROVIDER);
            provider.setBlocked(false);
            provider.setRole(role);
            provider.setPassword(passwordEncoder.encode(provider.getPassword()));
            provider.setStartWorkDate(new Date());
            providerService.save(provider);
            return REDIRECT;
        } else {
            model.addAttribute("loginIsOccupied", "Указанный логин уже занят");
            model.addAttribute(CITIES, cities);
            return "provider-create";
        }
    }

    @RequestMapping(value = "/admin/providers/{id}/edit", method = RequestMethod.POST)
    public String edit(Model model, @PathVariable UUID id, @Valid @ModelAttribute("provider") Provider provider,
                       BindingResult result) {
        List<City> cities = cityService.findAll();
        List<Role> roles = springUtil.getRoles();
        if (result.hasErrors()) {
            model.addAttribute(CITIES, cities);
            model.addAttribute(ROLES, roles);
        }

        Admin adminFromDB = adminService.findByUsernameAndBlocked(provider.getUsername(), false);
        Provider providerFromDB = providerService.findByUsernameAndBlocked(provider.getUsername(), false);
        if (adminFromDB == null && providerFromDB == null) {
            Role role = null;
            for (Role roleFromDB : roles) {
                if (roleFromDB.getName().equals(PROVIDER)) {
                    role = roleFromDB;
                }
            }
            provider.setRole(role);
            provider.setId(id);
            providerService.save(provider);
            return REDIRECT;
        } else {
            model.addAttribute("loginIsOccupied", "Указанный логин уже занят");
            model.addAttribute(CITIES, cities);
            model.addAttribute(ROLES, roles);
        }
        return "provider-edit";
    }

    @RequestMapping(value = "/admin/providers/{providerId}/products/add", method = RequestMethod.POST)
    public String addProduct(@PathVariable("providerId") UUID providerId,
                             @RequestParam("productId") UUID productId,
                             @RequestParam("amount") long amount,
                             @RequestParam("price") BigDecimal price) {
        Product product = productService.findById(productId);
        Provider provider = providerService.findById(providerId);

        ProductProvider productProviderFromDB = productProviderService.
                findByProviderIdAndProductId(providerId, productId);

        if (productProviderFromDB == null) {
            ProductProvider productProvider = new ProductProvider();
            productProvider.setProvider(provider);
            productProvider.setProduct(product);
            productProvider.setBlocked(false);
            productProviderService.save(productProvider);
        } else {
            productProviderService.save(productProviderFromDB);
        }
        return REDIRECT;
    }

    @Transactional
    @RequestMapping(value = "/admin/providers/{providerId}/shops/add", method = RequestMethod.POST)
    public String addShop(@PathVariable("providerId") UUID providerId,
                          @RequestParam("shopId") UUID shopId) {
        ShopProvider shopProviderFromDB = shopProviderService.findByProviderIdAndShopId(providerId, shopId);
        if (shopProviderFromDB == null) {
            Shop shop = shopService.findById(shopId);
            Provider provider = providerService.findById(providerId);
            ShopProvider shopProvider = new ShopProvider();
            shopProvider.setProvider(provider);
            shopProvider.setShop(shop);
            shopProvider.setBlocked(false);
            shopProviderService.save(shopProvider);
        }
        return REDIRECT;
    }

    @RequestMapping(value = "/admin/providers/{providerId}/products/{productId}/delete",
            method = RequestMethod.POST)
    public String deleteProduct(@PathVariable("productId") UUID productId,
                                @PathVariable("providerId") UUID providerId) {
        ProductProvider productProvider = productProviderService.findByProviderIdAndProductId(providerId, productId);
        productProviderService.delete(productProvider.getId());
        return "redirect:/admin/providers/" + providerId + "/products";
    }

    @RequestMapping(value = "/admin/providers/{providerId}/shops/{shopId}/lock", method = RequestMethod.POST)
    public String lockShop(@PathVariable("providerId") UUID providerId, @PathVariable("shopId") UUID shopId) {
        ShopProvider shopProvider = shopProviderService.findByProviderIdAndShopId(providerId, shopId);
        shopProviderService.lockById(shopProvider.getId());
        return "redirect:/admin/providers/" + providerId + "/shops";
    }

    @RequestMapping(value = "/admin/providers/{providerId}/shops/{shopId}/reestablish", method = RequestMethod.POST)
    public String reestablishShop(@PathVariable("providerId") UUID providerId,
                                  @PathVariable("shopId") UUID shopId) {
        ShopProvider shopProvider = shopProviderService.findByProviderIdAndShopId(providerId, shopId);
        shopProviderService.reestablishById(shopProvider.getId());
        return "redirect:/admin/providers/" + providerId + "/shops";
    }


    @RequestMapping(value = "/admin/providers/{providerId}/products/{productProviderId}/edit", method = RequestMethod.POST)
    public String providerProductEdit(@PathVariable("productProviderId") UUID productProviderId,
                                      @PathVariable("providerId") UUID providerId,
                                      @RequestParam("amount") int amount,
                                      @RequestParam("price") BigDecimal price) {
        ProductProvider productProvider = productProviderService.findById(productProviderId);
        productProviderService.save(productProvider);
        return "redirect:/admin/providers/" + providerId + "/products";
    }
//
//    @RequestMapping(value = "/replication/providers/add", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
//    @ResponseBody
//    public List<Provider> getProvidersFromClient(@RequestBody List<Provider> providers) {
//        for (Provider provider : providers) providerService.save(provider);
//        return providers;
//    }
//
//    @RequestMapping(value = "/replication/providers", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
//    @ResponseBody
//    public List<Provider> sendAllProvidersToClient() {
//        return providerService.findAll();
//    }

    @RequestMapping(value = "/replication/providers/time={time}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public List<Provider> sendNewProviderDataToClient(@PathVariable long time) {
        List<Provider> history = providerService.getHistory(time);
        System.out.println(history);
        return history;
    }
}
