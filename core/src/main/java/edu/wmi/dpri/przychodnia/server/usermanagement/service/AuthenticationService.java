package edu.wmi.dpri.przychodnia.server.usermanagement.service;

import edu.wmi.dpri.przychodnia.server.entity.User;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.ErrorMessage;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

import static edu.wmi.dpri.przychodnia.server.exceptionmanagement.generators.ErrorMessageGenerator.getAuthenticationErrorMessage;

/**
 * Created by lupus on 22.10.16.
 */
@Service
public class AuthenticationService {

    public static final String BAD_USERNAME_OR_PASSWORD_MSG = "BAD_USERNAME_OR_PASSWORD";
    public static final ErrorMessage BAD_USERNAME_OR_PASSWORD = getAuthenticationErrorMessage(BAD_USERNAME_OR_PASSWORD_MSG);
    @Inject
    private UserService userService;
    @Inject
    private PasswordService passwordService;

    public Boolean isPasswordValid(String username, String password) {
        User user = userService.getUserByLogin(username);
        if (user == null) {
            return false;
        }
        return passwordService.isLoginValid(password, user);
    }

    public User authenticateAndReturnUser(String username, String password) {
        User user = userService.getUserByLogin(username);
        if (user == null) {
            throw new BadCredentialsException(BAD_USERNAME_OR_PASSWORD_MSG);
        }
        if (passwordService.isLoginValid(password, user)) {
            return user;
        }
        throw new BadCredentialsException(BAD_USERNAME_OR_PASSWORD_MSG);
    }
}
