package kz.hts.ce.security;

import kz.hts.ce.entity.Admin;
import kz.hts.ce.entity.Provider;
import kz.hts.ce.service.AdminService;
import kz.hts.ce.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private AdminService adminService;
    @Autowired
    private ProviderService providerService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminService.findByUsernameAndBlocked(username, false);
        Provider provider = providerService.findByUsernameAndBlocked(username, false);

        GrantedAuthority authority;
        User user;
        if (admin == null && provider != null) {
            authority = new SimpleGrantedAuthority("ROLE_" + provider.getRole().getName());
            user = new User(provider.getUsername(), provider.getPassword(), Arrays.asList(authority));
        } else if (admin != null && provider == null) {
            authority = new SimpleGrantedAuthority("ROLE_" + admin.getRole().getName());
            user = new User(admin.getUsername(), admin.getPassword(), Arrays.asList(authority));
        } else throw new UsernameNotFoundException("User " + username + " not found.");
        return user;
    }
}
