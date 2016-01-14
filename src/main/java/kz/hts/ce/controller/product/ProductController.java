package kz.hts.ce.controller.product;

import kz.hts.ce.model.entity.*;
import kz.hts.ce.service.CategoryService;
import kz.hts.ce.service.ProductProviderService;
import kz.hts.ce.service.ProductService;
import kz.hts.ce.service.UnitService;
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
    public String createForProvider(Model model, @Valid @ModelAttribute("productProvider") ProductProvider productProvider, BindingResult result) {
        if (checkErrors(model, result)) return "product-create";
        else {
            productProvider.setProvider(springHelper.getAuthProvider());
            System.out.println(productProvider);

//            Product newProduct = productService.save(product);
//            ProductProvider productProvider = new ProductProvider();
//            productProvider.setProduct(newProduct);
//            productProvider.setProvider(springHelper.getAuthProvider());
//            productProviderService.save(productProvider);
            return REDIRECT;
        }
    }

    @RequestMapping(value = "/provider/products/{id}/edit", method = RequestMethod.POST)
    public String editForProvider(Model model, @PathVariable UUID id, @Valid @ModelAttribute("product") Product product, BindingResult result) {
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
