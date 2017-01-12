package edu.wmi.dpri.przychodnia.server.security.webapi;

import edu.wmi.dpri.przychodnia.server.security.jwt.model.JwtToken;
import edu.wmi.dpri.przychodnia.server.security.service.SecurityService;
import edu.wmi.dpri.przychodnia.server.security.service.TokenRefreshService;
import edu.wmi.dpri.przychodnia.server.security.webmodel.UserContextWebModel;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.inject.Inject;


@Service
@Profile({"secure","production"})
public class SecurityApiImpl implements SecurityApi {

    @Inject
    private TokenRefreshService tokenRefreshService;
    @Inject
    private SecurityService securityService;


    @Override
    public JwtToken refreshToken(RefreshTokenModel model) {
        return tokenRefreshService.refreshToken(model);
    }

    @Override
    public UserContextWebModel getUserContext() {
        return securityService.getUserContext();
    }
}
