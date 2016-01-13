package kz.hts.ce.util.converters;

import kz.hts.ce.model.entity.Unit;
import kz.hts.ce.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToUnit implements Converter<String, Unit>{

    @Autowired
    private UnitService unitService;

    public Unit convert(String source) {
        return unitService.findByName(source);
    }
}
