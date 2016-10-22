package edu.wmi.dpri.przychodnia.server.security.jwt;

import edu.wmi.dpri.przychodnia.server.security.config.JwtSettings;
import edu.wmi.dpri.przychodnia.server.security.jwt.model.RawAccessJwtToken;
import edu.wmi.dpri.przychodnia.server.security.model.UserContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lupus on 22.10.16.
 */
@Component
@Profile("secure")
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Inject
    private JwtSettings jwtSettings;


    @Override
    @SuppressWarnings("unchecked")
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        RawAccessJwtToken rawAccessToken = (RawAccessJwtToken) authentication.getCredentials();

        Jws<Claims> jwsClaims = rawAccessToken.parseClaims(jwtSettings.getTokenSigningKey());
        String subject = jwsClaims.getBody().getSubject();
        List<String> scopes = jwsClaims.getBody().get("scopes", List.class);
        List<GrantedAuthority> authorities = scopes.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        UserContext context = UserContext.create(subject, authorities);

        return new ClinicJwtAuthToken(context, context.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (ClinicJwtAuthToken.class.isAssignableFrom(authentication));
    }
}
