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

    @RequestMapping(value = "/admin/products/{id}/edit", method = RequestMethod.POST)
    public String editForAdmin
            (Model model, @PathVariable UUID id, @Valid @ModelAttribute("product") Product product, BindingResult result) {
        if (checkErrors(model, result)) return "product-edit";
        else {
            product.setId(id);
            productService.save(product);
            return REDIRECT;
        }
    }

    @RequestMapping(value = "/admin/products/create", method = RequestMethod.POST)
    public String createForAdmin(Model model, @Valid @ModelAttribute("product") Product product, BindingResult result) {
        if (checkErrors(model, result)) return "product-create";
        else {
            productService.save(product);
            return REDIRECT;
        }
    }

    @RequestMapping(value = "/provider/products/create", method = RequestMethod.POST)
    public String createForProvider(Model model, @Valid @ModelAttribute("productProvider") ProductProvider productProvider,
                                    BindingResult result) {
        if (checkErrors(model, result)) return "product-create";
        else {
            Product product = productService.save(productProvider.getProduct());
            productProvider.setProvider(springHelper.getAuthProvider());
            productProvider.setProduct(product);
            ProductProvider savedProductProvider = productProviderService.save(productProvider);

            Map<String, Integer> limits = productProvider.getLimits();
            Map<String, Type> typeMap = SpringHelper.typeMap;
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
            return REDIRECT;
        }
    }

    @RequestMapping(value = "/provider/products/{id}/edit", method = RequestMethod.POST)
    public String editForProvider(Model model, @PathVariable UUID id, @Valid @ModelAttribute("product") Product product,
                                  BindingResult result) {
        if (checkErrors(model, result)) return "product-edit";
        else {
            product.setId(id);
            productService.save(product);
            return REDIRECT;
        }
    }

    private boolean checkErrors(Model model, BindingResult result) {
        if (result.hasErrors()) {
            List<Category> categories = categoryService.findAll();
            List<Unit> units = unitService.findAll();
            model.addAttribute("categories", categories);
            model.addAttribute("units", units);
            return true;
        }
        return false;
    }
}
