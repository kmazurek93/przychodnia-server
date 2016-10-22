package edu.wmi.dpri.przychodnia.commons.usermanagement.webapi;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.LoginWebModel;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import static edu.wmi.dpri.przychodnia.commons.usermanagement.UserManagementConstants.API_PATH;
import static edu.wmi.dpri.przychodnia.commons.usermanagement.webapi.LoginApi.BASE_PATH;

/**
 * Created by lupus on 22.10.16.
 */
@Path(API_PATH + BASE_PATH)
public interface LoginApi {

    String BASE_PATH = "/login";

    @Path("/auth")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    Boolean login(LoginWebModel loginWebModel);
}
