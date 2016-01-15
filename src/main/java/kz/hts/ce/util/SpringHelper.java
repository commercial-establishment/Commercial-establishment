package kz.hts.ce.util;

import kz.hts.ce.model.entity.Gender;
import kz.hts.ce.model.entity.Provider;
import kz.hts.ce.model.entity.Role;
import kz.hts.ce.model.entity.Type;
import kz.hts.ce.service.GenderService;
import kz.hts.ce.service.ProviderService;
import kz.hts.ce.service.RoleService;
import kz.hts.ce.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class SpringHelper {

    public static Map<String, Role> roleMap;
    public static Map<String, Type> typeMap;
    private List<Role> roles;
    private List<Gender> genders;
    private List<Type> types;

    @Autowired
    private RoleService roleService;
    @Autowired
    private GenderService genderService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private ProviderService providerService;

    @PostConstruct
    public void initialize() {
        genders = genderService.findAll();

        roles = roleService.findAll();
        roleMap = new HashMap<>();
        for (Role role : roles) roleMap.put(role.getName(), role);

        types = typeService.findAll();
        typeMap = new HashMap<>();
        for (Type type : types) typeMap.put(type.getName(), type);
    }

    public static String getPrincipal() {
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    public UUID getAuthProviderId() {
        return providerService.findByUsername(getPrincipal()).getId();
    }

    public Provider getAuthProvider() {
        return providerService.findByUsername(getPrincipal());
    }

    public List<Role> getRoles() {
        return roles;
    }

    public List<Gender> getGenders() {
        return genders;
    }

    public List<Type> getTypes() {
        return types;
    }
}