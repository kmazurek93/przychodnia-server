package edu.wmi.dpri.przychodnia.server.security.ajax;

import edu.wmi.dpri.przychodnia.server.entity.*;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.ErrorMessage;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.auth.AuthenticationAppException;
import edu.wmi.dpri.przychodnia.server.security.model.UserContext;
import edu.wmi.dpri.przychodnia.server.usermanagement.service.AuthenticationService;
import edu.wmi.dpri.przychodnia.server.usermanagement.service.UserService;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lupus on 22.10.16.
 */
@Component
@Profile({"secure", "production"})
public class AjaxAuthenticationProvider implements AuthenticationProvider {
    @Inject
    private AuthenticationService authenticationService;
    @Inject
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.notNull(authentication, "No authentication data provided");

        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        User user = authenticationService.authenticateAndReturnUser(username, password);

        if (user.getRoles() == null) {
            ErrorMessage errorMessage = new ErrorMessage(403, "NO_ROLES");
            throw new AuthenticationAppException(errorMessage);
        }
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList());

        UserContext userContext = UserContext.create(user, authorities);
        return new UsernamePasswordAuthenticationToken(userContext, null, userContext.getAuthorities());
    }



    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}

