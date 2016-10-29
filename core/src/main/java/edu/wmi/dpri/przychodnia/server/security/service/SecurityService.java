package edu.wmi.dpri.przychodnia.server.security.service;

import edu.wmi.dpri.przychodnia.server.entity.Person;
import edu.wmi.dpri.przychodnia.server.entity.User;
import edu.wmi.dpri.przychodnia.server.security.model.UserContext;
import edu.wmi.dpri.przychodnia.server.security.webmodel.UserContextWebModel;
import edu.wmi.dpri.przychodnia.server.usermanagement.service.UserService;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by lupus on 22.10.16.
 */
@Service
@Profile("secure")
public class SecurityService {

    @Inject
    private UserService userService;

    public UserContextWebModel getUserContext() {
        return createUserContextModel();
    }

    private UserContextWebModel createUserContextModel() {
        UserContextWebModel contextWebModel = new UserContextWebModel();
        UserContext context = getUserContextFromContextHolder();
        contextWebModel.setUsername(context.getUsername());
        User user = userService.getUserByLogin(context.getUsername());
        fillPersonalData(contextWebModel, user);
        fillRoles(contextWebModel, context);
        return contextWebModel;
    }

    private void fillPersonalData(UserContextWebModel contextWebModel, User user) {
        Person person = user.getPerson();
        contextWebModel.setFirstName(person.getFirstName());
        contextWebModel.setLastName(person.getLastName());
        contextWebModel.setUserId(user.getId());
    }

    private void fillRoles(UserContextWebModel contextWebModel, UserContext context) {
        List<GrantedAuthority> authorities = context.getAuthorities();
        authorities.forEach(authority ->
                contextWebModel.getRolesAssigned().add(authority.getAuthority())
        );
    }

    private UserContext getUserContextFromContextHolder() {
        return (UserContext) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
    }


}
