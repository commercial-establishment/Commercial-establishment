package kz.hts.ce.controller.provider;


import kz.hts.ce.entity.*;
import kz.hts.ce.service.*;
import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
public class ProviderController {

    public static final String PROVIDER = "PROVIDER";

    @Autowired
    private ProviderService providerService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;
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

    @RequestMapping(value = "/admin/providers/{id}/lock")
    public String lock(@PathVariable long id) {
        Provider provider = providerService.findById(id);
        provider.setEndWorkDate(new Date());
        provider.setBlocked(true);
        providerService.save(provider);
        providerService.updateEndWorkDate(new Date(), id);
        providerService.lockById(id);
        return "redirect:";
    }

    @RequestMapping("/admin/providers/{id}/reestablish")
    public String reestablish(@PathVariable long id) {
        providerService.updateStartAndEndWorkDate(new Date(), null, id);
        providerService.reestablishById(id);
        return "redirect:";
    }

    @RequestMapping(value = "/admin/providers/create", method = RequestMethod.POST)
    public String create(@ModelAttribute("provider") Provider provider) {
        Role role = roleService.findByName("PROVIDER");
        provider.setRole(role);
        provider.setPassword(passwordEncoder.encode(provider.getPassword()));
        provider.setStartWorkDate(new Date());
        providerService.save(provider);
        return "redirect:";
    }

    @RequestMapping(value = "/admin/providers/{id}/edit", method = RequestMethod.POST)
    public String edit(Model model, @PathVariable long id, @Valid @ModelAttribute("provider") Provider provider, BindingResult result) {
        Role role = roleService.findByName(PROVIDER);
        provider.setRole(role);
        if (result.hasErrors()) {
            List<City> cities = cityService.findAll();
            List<Role> roles = roleService.findAll();

            model.addAttribute("cities", cities);
            model.addAttribute("roles", roles);
            return "provider-edit";
        }
        provider.setId(id);
        providerService.save(provider);
        return "redirect:";
    }

    @RequestMapping(value = "/admin/providers/{providerId}/products/add", method = RequestMethod.POST)
    public String addProduct(@PathVariable("providerId") long providerId,
                             @RequestParam("productId") long productId,
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
            productProvider.setPrice(price);
            productProvider.setAmount(amount);
            productProvider.setBlocked(false);
            productProviderService.save(productProvider);
        } else {
            productProviderFromDB.setPrice(price);
            productProviderFromDB.setAmount(amount);
            productProviderService.save(productProviderFromDB);
        }
        return "redirect:";
    }

    @Transactional
    @RequestMapping(value = "/admin/providers/{providerId}/shops/add", method = RequestMethod.POST)
    public String addShop(@PathVariable("providerId") long providerId,
                          @RequestParam("shopId") long shopId) {
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
        return "redirect:";
    }

    @RequestMapping(value = "/admin/providers/{providerId}/products/{productId}/delete",
            method = RequestMethod.POST)
    public String deleteProduct(@PathVariable("productId") long productId,
                                @PathVariable("providerId") long providerId) {
        ProductProvider productProvider = productProviderService.findByProviderIdAndProductId(providerId, productId);
        productProviderService.delete(productProvider.getId());
        return "redirect:/admin/providers/" + providerId + "/products";
    }

    @RequestMapping(value = "/admin/providers/{providerId}/shops/{shopId}/lock", method = RequestMethod.POST)
    public String lockShop(@PathVariable("providerId") long providerId, @PathVariable("shopId") long shopId) {
        ShopProvider shopProvider = shopProviderService.findByProviderIdAndShopId(providerId, shopId);
        shopProviderService.lockById(shopProvider.getId());
        return "redirect:/admin/providers/" + providerId + "/shops";
    }

    @RequestMapping(value = "/admin/providers/{providerId}/shops/{shopId}/reestablish", method = RequestMethod.POST)
    public String reestablishShop(@PathVariable("providerId") long providerId, @PathVariable("shopId") long shopId) {
        ShopProvider shopProvider = shopProviderService.findByProviderIdAndShopId(providerId, shopId);
        shopProviderService.reestablishById(shopProvider.getId());
        return "redirect:/admin/providers/" + providerId + "/shops";
    }


    @RequestMapping(value = "/admin/providers/{providerId}/products/{productProviderId}/edit", method = RequestMethod.POST)
    public String providerProductEdit(@PathVariable("productProviderId") long productProviderId,
                                      @PathVariable("providerId") long providerId,
                                      @RequestParam("amount") int amount,
                                      @RequestParam("price") BigDecimal price) {
        ProductProvider productProvider = productProviderService.findById(productProviderId);
        productProvider.setAmount(amount);
        productProvider.setPrice(price);
        productProviderService.save(productProvider);
        return "redirect:/admin/providers/" + providerId + "/products";
    }

    /*For provider*/
}
