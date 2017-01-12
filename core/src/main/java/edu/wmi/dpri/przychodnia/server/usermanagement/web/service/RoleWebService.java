package edu.wmi.dpri.przychodnia.server.usermanagement.web.service;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.RoleAssignmentWebModel;
import edu.wmi.dpri.przychodnia.server.entity.Role;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.auth.ForbiddenException;
import edu.wmi.dpri.przychodnia.server.usermanagement.service.RoleAssigningService;
import edu.wmi.dpri.przychodnia.server.usermanagement.service.RoleService;
import edu.wmi.dpri.przychodnia.server.usermanagement.service.verification.UserVerificationService;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

import static edu.wmi.dpri.przychodnia.server.exceptionmanagement.generators.ErrorMessageGenerator.getForbiddenErrorMessage;
import static edu.wmi.dpri.przychodnia.server.security.model.RoleAuthority.ROLE_ADMIN;


@Component
public class RoleWebService {

    public static final String INSUFFICIENT_PRIVILEGES = "INSUFFICIENT_PRIVILEGES";
    @Inject
    private RoleService roleService;
    @Inject
    private UserVerificationService userVerificationService;
    @Inject
    private RoleAssigningService roleAssigningService;

    public List<String> getAllPossibleRoles() {
        boolean isAdmin = userVerificationService.verifyIfHasAuthority(ROLE_ADMIN);
        if (isAdmin) {
            return roleService.findAllUnInitialized()
                    .stream().map(Role::getName)
                    .collect(Collectors.toList());
        } else {
            throw new ForbiddenException(getForbiddenErrorMessage(INSUFFICIENT_PRIVILEGES));
        }
    }

    public Response assignRole(RoleAssignmentWebModel roleAssignmentWebModel) {
        boolean isAdmin = userVerificationService.verifyIfHasAuthority(ROLE_ADMIN);
        if (isAdmin) {
            roleAssigningService.assignRole(roleAssignmentWebModel);
            return Response.noContent().build();
        } else {
            throw new ForbiddenException(getForbiddenErrorMessage(INSUFFICIENT_PRIVILEGES));
        }
    }

    public Response unassignRole(RoleAssignmentWebModel roleAssignmentWebModel) {
        boolean isAdmin = userVerificationService.verifyIfHasAuthority(ROLE_ADMIN);
        if (isAdmin) {
            roleAssigningService.unassignRole(roleAssignmentWebModel);
            return Response.noContent().build();
        } else {
            throw new ForbiddenException(getForbiddenErrorMessage(INSUFFICIENT_PRIVILEGES));
        }
    }

    public Response editRoles(RoleAssignmentWebModel roleAssignmentWebModel) {
        boolean isAdmin = userVerificationService.verifyIfHasAuthority(ROLE_ADMIN);
        if (isAdmin) {
            roleAssigningService.editRoles(roleAssignmentWebModel);
            return Response.noContent().build();
        } else {
            throw new ForbiddenException(getForbiddenErrorMessage(INSUFFICIENT_PRIVILEGES));
        }
    }
}
