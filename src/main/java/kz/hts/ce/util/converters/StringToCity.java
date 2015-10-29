package kz.hts.ce.util.converters;

import kz.hts.ce.entity.City;
import kz.hts.ce.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToCity implements Converter<String, City> {

    @Autowired
    private CityService cityService;

    public City convert(String source) {
        return cityService.findByName(source);
    }
}