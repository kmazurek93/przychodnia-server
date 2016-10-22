package edu.wmi.dpri.przychodnia.server.usermanagement.service;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.LoginWebModel;
import edu.wmi.dpri.przychodnia.server.entity.User;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by lupus on 22.10.16.
 */
@Service
public class LoginService {

    @Inject
    private UserService userService;
    @Inject
    private PasswordService passwordService;

    public Boolean isPasswordValid(LoginWebModel loginWebModel) {
        User user = userService.getUserByLogin(loginWebModel.getUsername());
        if(user == null) {
            return false;
        }
        return passwordService.isLoginValid(loginWebModel.getPassword(), user);
    }
}
