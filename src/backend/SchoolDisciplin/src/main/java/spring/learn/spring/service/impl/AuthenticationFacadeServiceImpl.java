package spring.learn.spring.service.impl;

import spring.learn.spring.service.AuthenticationFacadeService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationFacadeServiceImpl implements AuthenticationFacadeService {

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public boolean hasRole(Authentication authentication, String role) {
        return (authentication != null
                && authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(role)));
    }

    @Override
    public SecurityContext getContext() {
        return SecurityContextHolder.getContext();
    }
}