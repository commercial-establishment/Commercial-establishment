package kz.hts.ce.controller.product;

import kz.hts.ce.model.entity.Category;
import kz.hts.ce.model.entity.Product;
import kz.hts.ce.model.entity.Unit;
import kz.hts.ce.service.CategoryService;
import kz.hts.ce.service.ProductService;
import kz.hts.ce.service.UnitService;
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
    public String edit(Model model, @PathVariable UUID id, @Valid @ModelAttribute("product") Product product, BindingResult result) {
        if (result.hasErrors()) {
            List<Category> categories = categoryService.findAll();
            List<Unit> units = unitService.findAll();
            model.addAttribute("categories", categories);
            model.addAttribute("units", units);
            return "product-edit";
        }

        product.setId(id);
        productService.save(product);
        return REDIRECT;
    }

    @RequestMapping(value = "/admin/products/create-save", method = RequestMethod.POST)
    public String create(Model model, @Valid @ModelAttribute("product") Product product, BindingResult result) {
        if (result.hasErrors()) {
            List<Category> categories = categoryService.findAll();
            List<Unit> units = unitService.findAll();
            model.addAttribute("categories", categories);
            model.addAttribute("units", units);
            return "product-create";
        }
        productService.save(product);
        return REDIRECT;
    }

}
