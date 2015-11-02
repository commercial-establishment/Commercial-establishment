package kz.hts.ce.controller.provider;


import kz.hts.ce.entity.*;
import kz.hts.ce.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
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
    private ProviderPageController providerPageController;

    @RequestMapping(value = "/providers/{id}/lock")
    public String lock(@PathVariable long id) {
        Provider provider = providerService.findById(id);
        provider.setEndWorkDate(new Date());
        provider.setBlocked(true);
        providerService.save(provider);
        providerService.updateEndWorkDate(new Date(), id);
        providerService.lockById(id);
        return "redirect:";
    }

    @RequestMapping("/providers/{id}/reestablish")
    public String reestablish(@PathVariable long id) {
        providerService.updateStartAndEndWorkDate(new Date(), null, id);
        providerService.reestablishById(id);
        return "redirect:";
    }

    @RequestMapping(value = "/providers/{id}/edit", method = RequestMethod.POST)
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

    @RequestMapping(value = "/providers/create", method = RequestMethod.POST)
    public String create(@ModelAttribute("provider") Provider provider) {
        Role role = roleService.findByName("PROVIDER");
        provider.setRole(role);
        provider.setPassword(passwordEncoder.encode(provider.getPassword()));
        provider.setStartWorkDate(new Date());
        providerService.save(provider);
        return "redirect:";
    }

    @RequestMapping(value = "/providers/{providerId}/products/add", method = RequestMethod.POST)
    public String addProduct(Model model, @PathVariable("providerId") String providerId,
                             @RequestParam("productId") String productId,
                             @RequestParam("amount") long amount,
                             @RequestParam("price") BigDecimal price) {
        Product product = productService.findById(Long.valueOf(productId));
        Provider provider = providerService.findById(Long.valueOf(providerId));

        ProductProvider productProvider = new ProductProvider();
        productProvider.setProvider(provider);
        productProvider.setProduct(product);
        productProvider.setPrice(price);
        productProvider.setAmount(amount);
        productProvider.setBlocked(false);
        productProviderService.save(productProvider);
        return "redirect:";
    }
}
