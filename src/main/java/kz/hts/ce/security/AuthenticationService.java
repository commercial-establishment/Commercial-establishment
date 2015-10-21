package kz.hts.ce.security;

import kz.hts.ce.entity.Admin;
import kz.hts.ce.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sun.plugin.util.UserProfile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin userEntity = adminService.findByUsername(username);
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + userEntity.getRole().getName());
        return new User(userEntity.getUsername(), userEntity.getPassword(), Arrays.asList(authority));
    }

//    private List<GrantedAuthority> getGrantedAuthorities(Admin admin) {
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority("ROLE_" + admin.getRole().getName()));
//        return authorities;
//    }
}
