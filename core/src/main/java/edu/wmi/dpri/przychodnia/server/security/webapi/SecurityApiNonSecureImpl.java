package edu.wmi.dpri.przychodnia.server.security.webapi;

import edu.wmi.dpri.przychodnia.server.security.jwt.model.JwtToken;
import edu.wmi.dpri.przychodnia.server.security.webmodel.LoginWebModel;
import edu.wmi.dpri.przychodnia.server.security.webmodel.UserContextWebModel;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

/**
 * Created by lupus on 22.10.16.
 */
@Service
@Profile("!secure")
public class SecurityApiNonSecureImpl implements SecurityApi {

    @Override
    public JwtToken refreshToken(@Context HttpServletRequest request, @Context HttpServletResponse response) {
        return null;
    }

    @Override
    public UserContextWebModel getUserContext() {
        return null;
    }
}
