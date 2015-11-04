package kz.hts.ce.util;

import kz.hts.ce.entity.Provider;
import kz.hts.ce.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class SpringUtil {

    @Autowired
    private ProviderService providerService;

    public String getPrincipal() {
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

    public Provider getAuthorizedProvider() {
       return providerService.findByUsername(getPrincipal());
    }
}