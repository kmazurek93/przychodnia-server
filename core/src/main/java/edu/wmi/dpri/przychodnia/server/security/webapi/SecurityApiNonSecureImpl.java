package edu.wmi.dpri.przychodnia.server.security.webapi;

import edu.wmi.dpri.przychodnia.server.security.jwt.model.JwtToken;
import edu.wmi.dpri.przychodnia.server.security.webmodel.UserContextWebModel;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;


@Service
@Profile({"!secure", "!production"})
public class SecurityApiNonSecureImpl implements SecurityApi {


    @Override
    public JwtToken refreshToken(RefreshTokenModel model) {
        return null;
    }

    @Override
    public UserContextWebModel getUserContext() {
        return null;
    }
}
