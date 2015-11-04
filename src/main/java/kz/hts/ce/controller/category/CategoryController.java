package kz.hts.ce.controller.category;

import kz.hts.ce.entity.Category;
import kz.hts.ce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;


    @RequestMapping(value = "/admin/categories/{id}/edit", method = RequestMethod.POST)
    public String edit(Model model, @PathVariable long id, @Valid @ModelAttribute("category") Category category, BindingResult result) {
        if (result.hasErrors()) {
            return "category-edit";
        }
        category.setId(id);
        categoryService.save(category);
        return "redirect:";
    }

    @RequestMapping(value = "/admin/categories/create-save", method = RequestMethod.POST)
    public String create(Model model, @Valid @ModelAttribute("product") Category category, BindingResult result) {
        if (result.hasErrors()) {
            return "category-create";
        }
        categoryService.save(category);
        return "redirect:";
    }
}

