package kz.hts.ce.util;

import kz.hts.ce.entity.Gender;
import kz.hts.ce.entity.Role;
import kz.hts.ce.service.GenderService;
import kz.hts.ce.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SpringUtil {

    private Map<String, Role> roleMap;
    private List<Role> roles;
    private List<Gender> genders;

    @Autowired
    private RoleService roleService;
    @Autowired
    private GenderService genderService;

    @PostConstruct
    public void initialize() {
        roles = roleService.findAll();
        roleMap = new HashMap<>();
        for (Role role : roles) roleMap.put(role.getName(), role);

        genders = genderService.findAll();
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Gender> getGenders() {
        return genders;
    }

    public void setGenders(List<Gender> genders) {
        this.genders = genders;
    }

    public Map<String, Role> getRoleMap() {
        return roleMap;
    }

    public void setRoleMap(Map<String, Role> roleMap) {
        this.roleMap = roleMap;
    }
}