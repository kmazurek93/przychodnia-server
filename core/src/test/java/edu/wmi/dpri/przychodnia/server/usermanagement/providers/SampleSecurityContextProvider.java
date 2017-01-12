package edu.wmi.dpri.przychodnia.server.usermanagement.providers;

import edu.wmi.dpri.przychodnia.server.entity.User;
import edu.wmi.dpri.przychodnia.server.security.jwt.ClinicJwtAuthToken;
import edu.wmi.dpri.przychodnia.server.security.model.UserContext;
import edu.wmi.dpri.przychodnia.server.usermanagement.service.UserService;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.stream.Collectors;


@Component
@Profile("integration")
public class SampleSecurityContextProvider {
    @Inject
    private UserService userService;

    public void createSampleContextForUser(Long userId) {
        UserContext userContext = createUserContext(userId);
        SecurityContext ctxt = SecurityContextHolder.createEmptyContext();
        ctxt.setAuthentication(new ClinicJwtAuthToken(userContext, userContext.getAuthorities()));
        SecurityContextHolder.setContext(ctxt);
    }
    private UserContext createUserContext(Long userId) {
        User userById = userService.getUserById(userId);
        return UserContext.create(userById, userById.getRoles().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList()));
    }
}
