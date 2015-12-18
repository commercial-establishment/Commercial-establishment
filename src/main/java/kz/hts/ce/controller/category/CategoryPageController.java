package kz.hts.ce.controller.category;

import kz.hts.ce.entity.Category;
import kz.hts.ce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class CategoryPageController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/admin/categories/{id}")
    public String information(Model model, @PathVariable long id){
        Category category = categoryService.findById(id);
        model.addAttribute("category", category);
        return "/category-info";
    }

    @RequestMapping("/admin/categories/{id}/edit")
    public String edit(Model model, @PathVariable long id){
        Category category = categoryService.findById(id);
        model.addAttribute("category", category);
        return "category-edit";
    }

    @RequestMapping("/admin/categories/create")
    public String create(Model model){
        Category category = new Category();
        model.addAttribute("category", category);
        return "category-create";
    }

    @RequestMapping(value = "/admin/categories",method = RequestMethod.GET)
    public String categoriesPage(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "categories";
    }
}
