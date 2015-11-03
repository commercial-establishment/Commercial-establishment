package kz.hts.ce.util.converters;

import kz.hts.ce.entity.Category;
import kz.hts.ce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public final class StringToCategory implements Converter<String, Category> {

    @Autowired
    private CategoryService categoryService;

    public Category convert(String source) {
        return categoryService.findByName(source);
    }
}