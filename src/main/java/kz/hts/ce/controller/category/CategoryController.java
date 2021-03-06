package kz.hts.ce.controller.category;

import kz.hts.ce.model.entity.Category;
import kz.hts.ce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/categories/{id}/edit", method = RequestMethod.POST)
    public String edit(@PathVariable UUID id, @Valid @ModelAttribute("category") Category category,
                       BindingResult result) {
        if (result.hasErrors()) {
            return "category-edit";
        }
        category.setId(id);
        categoryService.save(category);
        return "redirect:";
    }

    @RequestMapping(value = "/categories/create-save", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("category") Category category, BindingResult result) {
        if (result.hasErrors()) {
            return "category-create";
        }
        categoryService.save(category);
        return "redirect:";
    }
}

