package edu.wmi.dpri.przychodnia.commons.usermanagement.webapi;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.RegistrationOutput;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.creation.RegistrationInputWebModel;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static edu.wmi.dpri.przychodnia.commons.usermanagement.UserManagementConstants.API_PATH;
import static edu.wmi.dpri.przychodnia.commons.usermanagement.webapi.RegistrationWebApi.BASE_PATH;

/**
 * Created by kmazu on 02.07.2016.
 */
@Path(API_PATH + BASE_PATH)
public interface RegistrationWebApi {
    String BASE_PATH = "/users/registration";

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    RegistrationOutput registerUser(RegistrationInputWebModel registrationInputWebModel);
}
