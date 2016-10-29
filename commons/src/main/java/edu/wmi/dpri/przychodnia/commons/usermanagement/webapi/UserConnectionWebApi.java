package edu.wmi.dpri.przychodnia.commons.usermanagement.webapi;


import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.creation.UserLinkingUnlinkingWebModel;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
    String CREATE = "/create";
    String REMOVE = "/remove";


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path(CREATE)
    Response connectUsers(@NotNull @Valid  UserLinkingUnlinkingWebModel model);


    @POST
    @Path(REMOVE)
    @Consumes(MediaType.APPLICATION_JSON)
    Response removeConnection(@NotNull @Valid UserLinkingUnlinkingWebModel model);


}
