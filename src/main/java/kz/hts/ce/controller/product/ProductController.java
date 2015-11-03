package kz.hts.ce.controller.product;

import kz.hts.ce.entity.Category;
import kz.hts.ce.entity.Product;
import kz.hts.ce.service.CategoryService;
import kz.hts.ce.service.ProductService;
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

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/products/{id}/lock")
         public String lock(@PathVariable long id) {
        Product product = productService.findById(id);
        product.setBlocked(true);
        productService.save(product);
        productService.lockById(id);
        return "redirect:";
    }

    @RequestMapping("/products/{id}/reestablish")
    public String reestablish(@PathVariable long id) {
        productService.reestablishById(id);
        return "redirect:";
    }

    @RequestMapping(value = "/products/{id}/edit", method = RequestMethod.POST)
    public String edit(Model model, @PathVariable long id, @Valid @ModelAttribute("product") Product product, BindingResult result) {
        if (result.hasErrors()) {
            List<Category> categories = categoryService.findAll();
            model.addAttribute("categories", categories);
            return "product-edit";
        }

        product.setId(id);
        productService.save(product);
        return "redirect:";
    }

    @RequestMapping(value = "/products/create-save", method = RequestMethod.POST)
    public String create(Model model, @Valid @ModelAttribute("product") Product product, BindingResult result) {
        if (result.hasErrors()) {
            return "product-create";
        }
        productService.save(product);
        return "redirect:";
    }

}
