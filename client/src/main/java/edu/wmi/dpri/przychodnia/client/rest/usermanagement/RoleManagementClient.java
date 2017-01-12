package edu.wmi.dpri.przychodnia.client.rest.usermanagement;

import edu.wmi.dpri.przychodnia.commons.rest.GenericRestClient;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webapi.RoleManagementWebApi;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.RoleAssignmentWebModel;

import javax.ws.rs.core.Response;
import java.util.List;

public class RoleManagementClient extends GenericRestClient<RoleManagementWebApi> {
    public RoleManagementClient(String url) {
        super(url, RoleManagementWebApi.class);
    }

    public List<String> getAllPossibleRoles() {
        return resource.getAllPossibleRoles();
    }

    public Response assignRole(RoleAssignmentWebModel roleAssignmentWebModel) {
        return resource.assignRole(roleAssignmentWebModel);
    }

    public Response unassignRole(RoleAssignmentWebModel roleAssignmentWebModel) {
        return resource.unassignRole(roleAssignmentWebModel);
    }

    public Response editRoles(RoleAssignmentWebModel roleAssignmentWebModel) {
        return resource.editRoles(roleAssignmentWebModel);
    }
}
