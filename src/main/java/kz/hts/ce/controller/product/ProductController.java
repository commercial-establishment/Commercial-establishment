package kz.hts.ce.controller.product;

import kz.hts.ce.model.entity.*;
import kz.hts.ce.service.*;
import kz.hts.ce.util.SpringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class ProductController {

    private static final String REDIRECT = "redirect:";

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UnitService unitService;
    @Autowired
    private ProductProviderService productProviderService;
    @Autowired
    private ProductLimitService productLimitService;

    @Autowired
    private SpringHelper springHelper;

    @RequestMapping(value = "/admin/products/{id}/lock")
    public String lock(@PathVariable UUID id) {
        Product product = productService.findById(id);
        product.setBlocked(true);
        productService.save(product);
        productService.lockById(id);
        return REDIRECT;
    }

    @RequestMapping("/admin/products/{id}/reestablish")
    public String reestablish(@PathVariable UUID id) {
        productService.reestablishById(id);
        return REDIRECT;
    }

    private String edit(Model model, BindingResult result, Product product, UUID id) {
        if (checkErrors(model, result)) return "product-edit";
        else {
            product.setId(id);
            productService.save(product);
            return REDIRECT;
        }
    }

    @RequestMapping(value = "/admin/products/{id}/edit", method = RequestMethod.POST)
    public String editForAdmin
            (Model model, @PathVariable UUID id, @Valid @ModelAttribute("product") Product product, BindingResult result) {
        return edit(model, result, product, id);
    }

    @RequestMapping(value = "/admin/products/create", method = RequestMethod.POST)
    public String createForAdmin(Model model, @Valid @ModelAttribute("product") Product product, BindingResult result) {
        if (checkErrors(model, result)) return "product-create";
        else {
            productService.save(product);
            return REDIRECT;
        }
    }

    private void setAttributesForProductCreateOrUpdatePage(Model model, String limitError) {
        List<Category> categories = categoryService.findAll();
        List<Unit> units = unitService.findAll();
        model.addAttribute("types", springHelper.getTypes());
        model.addAttribute("categories", categories);
        model.addAttribute("units", units);
        model.addAttribute("limitError", limitError);
    }

    private String checkLimits(String pageName, Model model, Map<String, Type> typeMap, Map<String, Integer> limits) {
        int limitSize = springHelper.getTypes().size() * 2;
        if (!(limitSize == limits.size())) {
            setAttributesForProductCreateOrUpdatePage(model, "Минимум не может быть больше либо равен максимуму.");
            return pageName;
        }
        for (Map.Entry<String, Type> typeEntry : typeMap.entrySet()) {
            String typeName = typeEntry.getKey();
            Integer min = limits.get("min" + typeName);
            Integer max = limits.get("max" + typeName);
            if (min >= max) {
                setAttributesForProductCreateOrUpdatePage(model, "Минимум не может быть больше либо равен максимуму.");
                return pageName;
            }
        }
        return null;
    }

    @RequestMapping(value = "/provider/products/create", method = RequestMethod.POST)
    public String createProductForProvider(Model model,
                                           @Valid @ModelAttribute("productProvider") ProductProvider productProvider,
                                           BindingResult result) {
        Map<String, Type> typeMap = SpringHelper.typeMap;
        Map<String, Integer> limits = productProvider.getLimits();
        checkLimits("product-create", model, typeMap, limits);

        if (checkErrors(model, result)) return "product-create";
        else {
            Product product = productService.save(productProvider.getProduct());
            productProvider.setProvider(springHelper.getAuthProvider());
            productProvider.setProduct(product);
            ProductProvider savedProductProvider = productProviderService.save(productProvider);

            for (Map.Entry<String, Type> typeEntry : typeMap.entrySet()) {
                ProductLimit productLimit = new ProductLimit();
                productLimit.setProductProvider(savedProductProvider);
                String typeName = typeEntry.getKey();
                Integer min = limits.get("min" + typeName);
                Integer max = limits.get("max" + typeName);
                productLimit.setType(typeEntry.getValue());
                productLimit.setMin(min);
                productLimit.setMax(max);
                productLimitService.save(productLimit);
            }
            return "redirect:/provider/products";
        }
    }

    @RequestMapping(value = "/provider/products/{id}/edit", method = RequestMethod.POST)
    public String editProductForProvider(Model model,
                                         @Valid @ModelAttribute("productProvider") ProductProvider productProvider,
                                         @PathVariable("id") UUID productProviderId,
                                         BindingResult result) {
        Map<String, Type> typeMap = SpringHelper.typeMap;
        Map<String, Integer> limits = productProvider.getLimits();
        checkLimits("product-edit", model, typeMap, limits);

        if (checkErrors(model, result)) return "product-edit";
        else {
            productProvider.setProvider(springHelper.getAuthProvider());
            productService.save(productProvider.getProduct());
            productProvider.setId(productProviderId);
            ProductProvider savedProductProvider = productProviderService.save(productProvider);

            for (Map.Entry<String, Type> typeEntry : typeMap.entrySet()) {
                String typeName = typeEntry.getKey();
                ProductLimit productLimit = productLimitService.
                        findByProductProviderIdAndTypeName(savedProductProvider.getId(), typeName);
                Integer min = limits.get("min" + typeName);
                Integer max = limits.get("max" + typeName);
                productLimit.setMin(min);
                productLimit.setMax(max);
                productLimitService.save(productLimit);
            }
            return "redirect:/provider/products";
        }
    }

    private boolean checkErrors(Model model, BindingResult result) {
        if (result.hasErrors()) {
            List<Category> categories = categoryService.findAll();
            List<Unit> units = unitService.findAll();
            model.addAttribute("types", springHelper.getTypes());
            model.addAttribute("categories", categories);
            model.addAttribute("units", units);
            return true;
        }
        return false;
    }

    @RequestMapping(value = "/provider/products/{productId}/lock", method = RequestMethod.POST)
    public String lockShop(@PathVariable("productId") UUID productId) {
        UUID providerId = springHelper.getAuthProviderId();
        ProductProvider productProvider = productProviderService.findByProviderIdAndProductId(providerId, productId);
        productProviderService.lockById(productProvider.getId());
        return "redirect:/provider/products";
    }

    @RequestMapping(value = "/provider/products/{productId}/reestablish", method = RequestMethod.POST)
    public String reestablishShop(@PathVariable("productId") UUID productId) {
        UUID providerId = springHelper.getAuthProviderId();
        ProductProvider productProvider = productProviderService.findByProviderIdAndProductId(providerId, productId);
        productProviderService.reestablishById(productProvider.getId());
        return "redirect:/provider/products";
    }
}
