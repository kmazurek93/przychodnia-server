package edu.wmi.dpri.przychodnia.server.security.jwt;

import edu.wmi.dpri.przychodnia.server.entity.User;
import edu.wmi.dpri.przychodnia.server.security.config.JwtSettings;
import edu.wmi.dpri.przychodnia.server.security.jwt.model.RawAccessJwtToken;
import edu.wmi.dpri.przychodnia.server.security.model.UserContext;
import edu.wmi.dpri.przychodnia.server.usermanagement.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

import static edu.wmi.dpri.przychodnia.server.usermanagement.service.AuthenticationService.EXPIRED_ACCOUNT_MSG;

/**
 * Created by lupus on 22.10.16.
 */
@Component
@Profile({"secure","production"})
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Inject
    private JwtSettings jwtSettings;
    @Inject
    private UserService userService;

    @Override
    @SuppressWarnings("unchecked")
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        RawAccessJwtToken rawAccessToken = (RawAccessJwtToken) authentication.getCredentials();

        Jws<Claims> jwsClaims = rawAccessToken.parseClaims(jwtSettings.getTokenSigningKey());
        String username = jwsClaims.getBody().getSubject();
        List<String> scopes = jwsClaims.getBody().get("scopes", List.class);
        List<GrantedAuthority> authorities = scopes.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        User userByLogin = userService.getUserByLogin(username);
        if (!userByLogin.isActive()) {
            throw new BadCredentialsException(EXPIRED_ACCOUNT_MSG);
        }
        UserContext context = UserContext.create(username, userByLogin.getId(), authorities);

        return new ClinicJwtAuthToken(context, context.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (ClinicJwtAuthToken.class.isAssignableFrom(authentication));
    }
}
