package edu.wmi.dpri.przychodnia.server.usermanagement.web.service;

import edu.wmi.dpri.przychodnia.server.security.webmodel.LoginWebModel;
import edu.wmi.dpri.przychodnia.server.usermanagement.service.AuthenticationService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by lupus on 22.10.16.
 */
@Service
public class LoginWebService {
    @Inject
    private AuthenticationService authenticationService;

    public Boolean isPasswordValid(LoginWebModel loginWebModel) {
        return authenticationService.isPasswordValid(loginWebModel.getUsername(), loginWebModel
                .getPassword());
    }
}
