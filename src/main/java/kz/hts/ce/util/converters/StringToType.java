package kz.hts.ce.util.converters;

import kz.hts.ce.model.entity.Type;
import kz.hts.ce.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToType implements Converter<String, Type> {

    @Autowired
    private TypeService typeService;

    public Type convert(String source) {
        return typeService.findByName(source);
    }
}