package kz.hts.ce.util.converters;

import kz.hts.ce.entity.Area;
import kz.hts.ce.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToArea implements Converter<String, Area> {

    @Autowired
    private AreaService areaService;

    public Area convert(String source) {
        return areaService.findByName(source);
    }
}