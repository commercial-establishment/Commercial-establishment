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
import static kz.hts.ce.util.Helper.convertStringToBigDecimal;

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
    public String providers(Model model, @PathVariable long id) {
        List<ProductProvider> providerProducts = productProviderService.findByProviderId(id);
        model.addAttribute("providerProducts", providerProducts);
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
    public String admins(Model model, @PathVariable("providerId") long providerId) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "provider-product-add";
    }

    @RequestMapping(value = "/providers/{providerId}/products", method = RequestMethod.POST)
    public String providerProductsPost(Model model, @PathVariable("providerId") long providerId,
                                       @RequestParam("productId") String productId,
                                       @RequestParam("amount") long amount, @RequestParam("price") BigDecimal price) {
        Product product = productService.findById(Long.valueOf(productId));
        Provider provider = providerService.findById(providerId);

        ProductProvider productProvider = new ProductProvider();
        productProvider.setProvider(provider);
        productProvider.setProduct(product);
        productProvider.setPrice(price);
        productProvider.setAmount(amount);
        productProvider.setBlocked(false);
        productProviderService.save(productProvider);
        return providers(model, providerId);
    }
}
