package edu.wmi.dpri.przychodnia.server.usermanagement.web.service;

import edu.wmi.dpri.przychodnia.server.entity.User;
import edu.wmi.dpri.przychodnia.server.usermanagement.function.UserRegisteringStateFunctions;
import edu.wmi.dpri.przychodnia.server.usermanagement.service.UserService;
import edu.wmi.dpri.przychodnia.server.usermanagement.state.UserRegisteringState;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by lupus on 18.10.16.
 */
@Service
public class UserWebService {

    @Inject
    private UserRegisteringStateFunctions userRegisteringStateFunctions;

    @Inject
    private UserService userService;

    public void handleAddingUserDuringRegistration(UserRegisteringState state) {
        User user = userRegisteringStateFunctions.createUserToSaveFromState(state);
        User savedUser = userService.saveUser(user);
        state.setSavedUser(savedUser);
    }
}
