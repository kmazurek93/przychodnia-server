package edu.wmi.dpri.przychodnia.server.security.service;

import edu.wmi.dpri.przychodnia.server.entity.User;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.ErrorMessage;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.auth.AuthenticationAppException;
import edu.wmi.dpri.przychodnia.server.security.config.JwtSettings;
import edu.wmi.dpri.przychodnia.server.security.config.WebSecurityConfig;
import edu.wmi.dpri.przychodnia.server.security.jwt.JwtTokenExtractor;
import edu.wmi.dpri.przychodnia.server.security.jwt.JwtTokenFactory;
import edu.wmi.dpri.przychodnia.server.security.jwt.model.JwtToken;
import edu.wmi.dpri.przychodnia.server.security.jwt.model.RawAccessJwtToken;
import edu.wmi.dpri.przychodnia.server.security.jwt.model.RefreshToken;
import edu.wmi.dpri.przychodnia.server.security.jwt.verifier.TokenVerifier;
import edu.wmi.dpri.przychodnia.server.security.model.UserContext;
import edu.wmi.dpri.przychodnia.server.usermanagement.service.UserService;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

import static edu.wmi.dpri.przychodnia.server.exceptionmanagement.generators.ErrorMessageGenerator.getAuthenticationErrorMessage;

/**
 * Created by lupus on 22.10.16.
 */
@Component
@Profile({"secure","production"})
public class TokenRefreshService {

    @Inject
    private JwtTokenExtractor tokenExtractor;
    @Inject
    private JwtSettings jwtSettings;
    @Inject
    private TokenVerifier tokenVerifier;
    @Inject
    private UserService userService;
    @Inject
    private JwtTokenFactory tokenFactory;

    public JwtToken refreshToken(HttpServletRequest request, HttpServletResponse response) {
        String tokenPayload = tokenExtractor.extract(request.getHeader(WebSecurityConfig.JWT_TOKEN_HEADER_PARAM));

        RawAccessJwtToken rawToken = new RawAccessJwtToken(tokenPayload);
        RefreshToken refreshToken = RefreshToken.create(rawToken, jwtSettings.getTokenSigningKey())
                .orElseThrow(this::invalidTokenException);

        String jti = refreshToken.getJti();
        if (!tokenVerifier.verify(jti)) {
            throw invalidTokenException();
        }

        String subject = refreshToken.getSubject();
        User user = userService.getUserByLogin(subject);
        verifyUserAndItsRoles(user);
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList());

        UserContext userContext = UserContext.create(user, authorities);

        return tokenFactory.createAccessJwtToken(userContext);
    }

    private AuthenticationAppException invalidTokenException() {
        ErrorMessage invalid_token = getAuthenticationErrorMessage("INVALID_TOKEN");
        return new AuthenticationAppException(invalid_token);
    }

    private void verifyUserAndItsRoles(User user) {
        if (user == null) {
            ErrorMessage invalidUser = getAuthenticationErrorMessage("INVALID_USER");
            throw new AuthenticationAppException(invalidUser);
        }

        if (user.getRoles() == null) {
            ErrorMessage noRoles = getAuthenticationErrorMessage("NO_ROLES");
            throw new AuthenticationAppException(noRoles);
        }
    }
}
