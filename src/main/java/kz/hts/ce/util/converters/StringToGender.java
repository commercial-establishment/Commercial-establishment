package kz.hts.ce.util.converters;

import kz.hts.ce.model.entity.Gender;
import kz.hts.ce.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public final class StringToGender implements Converter<String, Gender> {

    @Autowired
    private GenderService genderService;

    public Gender convert(String source) {
        return genderService.findByName(source);
    }
}