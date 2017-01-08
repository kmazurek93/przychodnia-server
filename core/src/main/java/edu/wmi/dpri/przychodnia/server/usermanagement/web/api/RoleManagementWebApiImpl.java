package edu.wmi.dpri.przychodnia.server.usermanagement.web.api;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webapi.RoleManagementWebApi;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.RoleAssignmentWebModel;
import edu.wmi.dpri.przychodnia.server.usermanagement.web.service.RoleWebService;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by lupus on 24.11.16.
 */
public class RoleManagementWebApiImpl implements RoleManagementWebApi {

    @Inject
    private RoleWebService roleWebService;

    @Override
    public List<String> getAllPossibleRoles() {
        return roleWebService.getAllPossibleRoles();
    }

    @Override
    public Response assignRole(RoleAssignmentWebModel roleAssignmentWebModel) {
        return roleWebService.assignRole(roleAssignmentWebModel);
    }

    @Override
    public Response unassignRole(RoleAssignmentWebModel roleAssignmentWebModel) {
        return roleWebService.unassignRole(roleAssignmentWebModel);
    }

    @Override
    public Response editRoles(RoleAssignmentWebModel roleAssignmentWebModel) {
        return roleWebService.editRoles(roleAssignmentWebModel);
    }
}
