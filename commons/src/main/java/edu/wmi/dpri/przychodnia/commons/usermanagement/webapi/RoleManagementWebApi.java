package edu.wmi.dpri.przychodnia.commons.usermanagement.webapi;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.RoleAssignmentWebModel;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

import static edu.wmi.dpri.przychodnia.commons.usermanagement.UserManagementConstants.API_PATH;
import static edu.wmi.dpri.przychodnia.commons.usermanagement.webapi.RoleManagementWebApi.BASE_PATH;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by kmazu on 02.07.2016.
 */
@Path(API_PATH + BASE_PATH)
public interface RoleManagementWebApi {
    String BASE_PATH = "/roles";

    @GET
    @Produces(APPLICATION_JSON)
    List<String> getAllPossibleRoles();

    @POST
    @Path("/assign")
    @Consumes(APPLICATION_JSON)
    Response assignRole(RoleAssignmentWebModel roleAssignmentWebModel);

    @POST
    @Path("/unassign")
    @Consumes(APPLICATION_JSON)
    Response unassignRole(RoleAssignmentWebModel roleAssignmentWebModel);

    @POST
    @Path("/edit")
    @Consumes(APPLICATION_JSON)
    Response editRoles(RoleAssignmentWebModel roleAssignmentWebModel);
}
