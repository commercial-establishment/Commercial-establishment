package kz.hts.ce.util;

import kz.hts.ce.entity.Gender;
import kz.hts.ce.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

//@TypeConverter
public final class StringToGender implements Converter<String, Gender> {

//    @Autowired
//    private GenderService genderService;

    public Gender convert(String source) {
//        Gender gender = genderService.findByName(source);
        Gender gender = new Gender();
        gender.setName(source);
        gender.setId(1);
        return gender;
    }
}