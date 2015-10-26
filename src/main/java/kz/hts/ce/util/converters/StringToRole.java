package kz.hts.ce.util.converters;

import kz.hts.ce.entity.Role;
import kz.hts.ce.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public final class StringToRole implements Converter<String, Role> {

    @Autowired
    private RoleService roleService;

    public Role convert(String source) {
        return roleService.findByName(source);
    }
}