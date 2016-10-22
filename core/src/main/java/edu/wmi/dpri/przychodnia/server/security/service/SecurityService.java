package edu.wmi.dpri.przychodnia.server.security.service;

import edu.wmi.dpri.przychodnia.server.security.model.UserContext;
import edu.wmi.dpri.przychodnia.server.security.webmodel.UserContextWebModel;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lupus on 22.10.16.
 */
@Service
@Profile("secure")
public class SecurityService {

    public UserContextWebModel getUserContext() {
        return createUserContextModel();
    }

    private UserContextWebModel createUserContextModel() {
        UserContextWebModel contextWebModel = new UserContextWebModel();
        UserContext context = getUserContextFromContextHolder();
        contextWebModel.setUsername(context.getUsername());
        fillRoles(contextWebModel, context);
        return contextWebModel;
    }

    private void fillRoles(UserContextWebModel contextWebModel, UserContext context) {
        List<GrantedAuthority> authorities = context.getAuthorities();
        authorities.forEach(authority ->
                contextWebModel.getRolesAssigned().add(authority.getAuthority())
        );
    }

    private UserContext getUserContextFromContextHolder() {
        return (UserContext) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
    }


}
