package kz.hts.ce.controller;

import kz.hts.ce.entity.*;
import kz.hts.ce.service.CityService;
import kz.hts.ce.service.ProductProviderService;
import kz.hts.ce.service.ProviderService;
import kz.hts.ce.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProviderPageController {

    public static final String PROVIDER = "PROVIDER";

    @Autowired
    private ProviderService providerService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private CityService cityService;
    @Autowired
    private ProductProviderService productProviderService;

    @RequestMapping("/providers/{id}")
    public String providerInformation(Model model, @PathVariable long id) {
        Provider provider = providerService.findById(id);
        model.addAttribute("provider", provider);
        return "/provider-info";
    }

    @RequestMapping("/providers/{id}/edit")
    public String edit(Model model, @PathVariable long id) {
        List<City> cities = cityService.findAll();
        Provider provider = providerService.findById(id);
        List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        model.addAttribute("provider", provider);
        model.addAttribute("cities", cities);
        return "provider-edit";
    }

    @RequestMapping("/providers/create")
    public String create(Model model) {
        Provider provider = new Provider();
        model.addAttribute("provider", provider);
        return "provider-create";
    }

    @RequestMapping(value = "/providers", method = RequestMethod.GET)
    public String providers(Model model) {
        List<Provider> providers = providerService.findByRoleName(PROVIDER);
        model.addAttribute("providers", providers);
        return "providers";
    }

    @RequestMapping(value = "/providers/{id}/products", method = RequestMethod.GET)
    public String providers(Model model, @PathVariable long id) {
        List<ProductProvider> providerProducts = productProviderService.findByProviderId(id);

        List<Product> products = new ArrayList<>();
        for (ProductProvider providerProduct : providerProducts) {
            Product product = providerProduct.getProduct();
            products.add(product);
        }

        model.addAttribute("products", products);
        return "products";
    }

    @RequestMapping(value = "/providers/{id}/products/{providerId}", method = RequestMethod.GET)
    public String providerProducts(Model model, @PathVariable("id") long id, @PathVariable("providerId") long providerId) {
        return "product-info";
    }
}
