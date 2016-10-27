package edu.wmi.dpri.przychodnia.server.security.webapi;

import edu.wmi.dpri.przychodnia.server.security.jwt.model.JwtToken;
import edu.wmi.dpri.przychodnia.server.security.service.SecurityService;
import edu.wmi.dpri.przychodnia.server.security.service.TokenRefreshService;
import edu.wmi.dpri.przychodnia.server.security.webmodel.LoginWebModel;
import edu.wmi.dpri.przychodnia.server.security.webmodel.UserContextWebModel;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

/**
 * Created by lupus on 22.10.16.
 */
@Service
@Profile("secure")
public class SecurityApiImpl implements SecurityApi {

    @Inject
    private TokenRefreshService tokenRefreshService;

    @Inject
    private SecurityService securityService;

    @Override
    public JwtToken refreshToken(@Context HttpServletRequest request, @Context HttpServletResponse response) {
        return tokenRefreshService.refreshToken(request, response);
    }

    @Override
    public UserContextWebModel getUserContext() {
        return securityService.getUserContext();
    }
}
