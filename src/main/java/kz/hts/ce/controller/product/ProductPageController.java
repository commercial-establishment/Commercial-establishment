package kz.hts.ce.controller.product;

import kz.hts.ce.entity.Category;
import kz.hts.ce.entity.Product;
import kz.hts.ce.service.CategoryService;
import kz.hts.ce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ProductPageController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;



    @RequestMapping("/admin/products/{id}")
    public String information(Model model, @PathVariable long id){
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "/product-info(2)";
    }

    @RequestMapping("/admin/products/{id}/edit")
    public String edit(Model model, @PathVariable long id){
        List<Category> categories = categoryService.findAll();
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        return "product-edit";
    }

    @RequestMapping("/admin/products/create")
    public String create(Model model){
        Product product = new Product();
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("product", product);
        return "product-create";
    }

    @RequestMapping(value = "/admin/products",method = RequestMethod.GET)
    public String shopsPage(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "products";
    }

}
