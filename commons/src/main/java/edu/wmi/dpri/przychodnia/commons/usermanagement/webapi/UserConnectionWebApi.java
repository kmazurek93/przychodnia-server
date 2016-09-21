package edu.wmi.dpri.przychodnia.commons.usermanagement.webapi;


import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.UserConnectionWebModel;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.creation.UserConnectionCreationDeletionWebModel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static edu.wmi.dpri.przychodnia.commons.usermanagement.UserManagementConstants.API_PATH;
import static edu.wmi.dpri.przychodnia.commons.usermanagement.webapi.UserConnectionWebApi.BASE_PATH;


/**
 * Created by kmazu on 03.07.2016.
 */
@Path(API_PATH + BASE_PATH)
public interface UserConnectionWebApi {

    String BASE_PATH = "/userConnections";


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    UserConnectionWebModel connectUsers(UserConnectionCreationDeletionWebModel model);


    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    Response removeConnection(UserConnectionCreationDeletionWebModel model);


}
