package edu.wmi.dpri.przychodnia.server.usermanagement.web.api;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webapi.LoginApi;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.LoginWebModel;
import edu.wmi.dpri.przychodnia.server.usermanagement.web.service.LoginWebService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by lupus on 22.10.16.
 */
@Service
public class LoginApiImpl implements LoginApi {
    @Inject
    private LoginWebService loginWebService;

    @Override
    public Boolean login(LoginWebModel loginWebModel) {
        return loginWebService.isPasswordValid(loginWebModel);
    }
}
