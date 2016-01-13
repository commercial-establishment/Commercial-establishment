package kz.hts.ce.util;

import kz.hts.ce.model.entity.Gender;
import kz.hts.ce.model.entity.Provider;
import kz.hts.ce.model.entity.Role;
import kz.hts.ce.service.GenderService;
import kz.hts.ce.service.ProviderService;
import kz.hts.ce.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class SpringUtil {

    public static Map<String, Role> roleMap;
    private List<Role> roles;
    private List<Gender> genders;
    private Date broadcastDate;

    @Autowired
    private RoleService roleService;
    @Autowired
    private GenderService genderService;

    @Autowired
    private ProviderService providerService;

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

    public UUID getAuthProviderId() {
        return providerService.findByUsername(getPrincipal()).getId();
    }

    public Provider getAuthProvider() {
        return providerService.findByUsername(getPrincipal());
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

    public Date getBroadcastDate() {
        return broadcastDate;
    }

    public void setBroadcastDate(Date broadcastDate) {
        this.broadcastDate = broadcastDate;
    }
}