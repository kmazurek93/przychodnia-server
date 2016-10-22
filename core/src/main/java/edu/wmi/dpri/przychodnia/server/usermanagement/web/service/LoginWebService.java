package edu.wmi.dpri.przychodnia.server.usermanagement.web.service;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.LoginWebModel;
import edu.wmi.dpri.przychodnia.server.usermanagement.service.LoginService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by lupus on 22.10.16.
 */
@Service
public class LoginWebService {
    @Inject
    private LoginService loginService;

    public Boolean isPasswordValid(LoginWebModel loginWebModel) {
        return loginService.isPasswordValid(loginWebModel);
    }
}
