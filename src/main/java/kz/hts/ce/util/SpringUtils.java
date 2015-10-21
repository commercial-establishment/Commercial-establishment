package kz.hts.ce.util;

import kz.hts.ce.security.SecurityUser;
import org.springframework.security.core.context.SecurityContextHolder;

public class SpringUtils {
    public static SecurityUser getCurrentlyAuthenticatedUser() {
        return (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}