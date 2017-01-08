package edu.wmi.dpri.przychodnia.server.usermanagement.service;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.RoleAssignmentWebModel;
import edu.wmi.dpri.przychodnia.server.entity.Role;
import edu.wmi.dpri.przychodnia.server.entity.User;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.ExceptionCause;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static edu.wmi.dpri.przychodnia.server.exceptionmanagement.NotFoundExceptionThrower.throwExceptionIfNull;

/**
 * Created by lupus on 24.11.16.
 */
@Component
public class RoleAssigningService {
    @Inject
    private UserService userService;
    @Inject
    private RoleService roleService;


    public void assignRole(RoleAssignmentWebModel model) {
        User user = userService.getUserById(model.getUserId());
        for(String r : model.getRoles()) {
            Role role = roleService.findByName(r);
            throwExceptionIfNull(r, role, ExceptionCause.RETRIEVAL, Role.class);
            roleService.assignRole(user, role);
        }

    }
    public void unassignRole(RoleAssignmentWebModel model) {
        User user = userService.getUserById(model.getUserId());
        for(String r : model.getRoles()) {
            Role role = roleService.findByName(r);
            throwExceptionIfNull(r, role, ExceptionCause.RETRIEVAL, Role.class);
            roleService.unassignRole(user, role);
        }
    }

    public void editRoles(RoleAssignmentWebModel model) {
        User user = userService.getUserById(model.getUserId());

        List<Role> roles = newArrayList();

        for(String r : model.getRoles()) {
            Role role = roleService.findByName(r);
            throwExceptionIfNull(r, role, ExceptionCause.RETRIEVAL, Role.class);
            roles.add(role);
        }
        roleService.editRoles(user, roles);
    }
}
