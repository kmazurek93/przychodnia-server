package edu.wmi.dpri.przychodnia.commons.usermanagement.webapi;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.UserCrudWebModel;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.UserSearchResult;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.UserSearchWebModel;

import javax.ws.rs.*;

import static edu.wmi.dpri.przychodnia.commons.usermanagement.UserManagementConstants.API_PATH;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by lupus on 12.11.16.
 */
@Path(API_PATH + UserWebApi.USERS)
public interface UserWebApi {

    String USERS = "/users";


    @GET
    @Produces(APPLICATION_JSON)
    @Path("/{id}")
    UserCrudWebModel getUserById(@PathParam("id") Long id);

    @PUT
    @Path("/{id}")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    UserCrudWebModel updateUser(@PathParam("id") Long id, UserCrudWebModel model);

    @DELETE
    @Path("/{id}")
    void archivizeUser(@PathParam("id") Long id);

    @POST
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    //if model is null then return ALL.
    UserSearchResult queryUsers(UserSearchWebModel model);
}
