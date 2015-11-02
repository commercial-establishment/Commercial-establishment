package kz.hts.ce.controller.provider;

import kz.hts.ce.entity.*;
import kz.hts.ce.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

import static kz.hts.ce.util.Helper.calculateCost;

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
    @Autowired
    private ProductService productService;

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
    public String products(Model model, @PathVariable long id) {
        List<ProductProvider> providerProducts = productProviderService.findByProviderId(id);
        model.addAttribute("providerProducts", providerProducts);
        model.addAttribute("providerId", id);
        return "provider-products";
    }

    @RequestMapping(value = "/providers/{providerId}/products/{productProviderId}", method = RequestMethod.GET)
    public String providerProducts(Model model, @PathVariable("productProviderId") long productProviderId) {
        ProductProvider productProvider = productProviderService.findById(productProviderId);

        long amount = productProvider.getAmount();
        BigDecimal price = productProvider.getPrice();
        BigDecimal sumPrice = calculateCost(amount, price);

        model.addAttribute("productProvider", productProvider);
        model.addAttribute("sumPrice", sumPrice);
        return "product-info";
    }

    @RequestMapping(value = "/providers/{providerId}/products/all", method = RequestMethod.GET)
    public String providerProductAdd(Model model, @PathVariable("providerId") String providerId) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        model.addAttribute("providerId", providerId);
        return "provider-product-add";
    }
}
