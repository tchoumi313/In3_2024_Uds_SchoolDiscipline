package spring.learn.spring.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;

public interface AuthenticationFacadeService {

    Authentication getAuthentication();

    boolean hasRole(Authentication authentication, String role);

    SecurityContext getContext();
}