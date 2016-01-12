package kz.hts.ce.controller.replication;

import kz.hts.ce.model.entity.Category;
import kz.hts.ce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CategoryReplicationController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/replication/categories/time={time}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public List<Category> sendNewCategoriesDataToClient(@PathVariable long time) {
        List<Category> categories;
        if (time == 0) categories = categoryService.findAll();
        else categories = categoryService.getHistory(time);
        return categories;
    }
}
