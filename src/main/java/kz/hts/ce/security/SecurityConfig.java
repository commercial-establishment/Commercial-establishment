package kz.hts.ce.security;

import kz.hts.ce.config.CustomSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private CustomSuccessHandler successHandler;

    @Autowired
    @Qualifier("authenticationService")
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
        dao.setUserDetailsService(authenticationService);
        dao.setPasswordEncoder(passwordEncoder());
        return dao;
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login/form", "/recovery").anonymous()
                .antMatchers("/", "/home", "/home/**").hasAnyRole("ADMIN", "PROVIDER")
                .antMatchers("/admin", "/admin/**").hasAnyRole("ADMIN")
                .antMatchers("/provider", "/provider/**").hasAnyRole("PROVIDER")
                .antMatchers("/replication/*").hasAnyRole("OWNER", "ACCOUNTANT", "SELLER")
                .anyRequest().authenticated();
        http
                .formLogin()
                .loginPage("/login/form")
                .loginProcessingUrl("/j_spring_security_check")
                .successHandler(successHandler)
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                .failureUrl("/login?error").and()
                .httpBasic();
        http
                .logout().logoutSuccessUrl("/login?logout")
                .and()
                .exceptionHandling().accessDeniedPage("/403");
    }

//    @Bean
//    public RememberMeServices rememberMeServices() {
//        TokenBasedRememberMeServices rememberMeServices = new TokenBasedRememberMeServices("password", userService);
//        rememberMeServices.setCookieName("cookieName");
//        rememberMeServices.setParameter("rememberMe");
//        return rememberMeServices;
//    }
}