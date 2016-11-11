package edu.wmi.dpri.przychodnia.commons.usermanagement.webapi;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.IdTypeWebModel;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.RegistrationOutput;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.SexWebModel;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.creation.RegistrationInputWebModel;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.util.List;

import static edu.wmi.dpri.przychodnia.commons.usermanagement.webapi.PublicWebApi.BASE_PATH;

/**
 * Created by kmazu on 02.07.2016.
 */
@Path(BASE_PATH)
public interface PublicWebApi {
    String BASE_PATH = "/public";
    String REGISTRATION = "/registration";

    @POST
    @Path(REGISTRATION)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    RegistrationOutput registerUser(@NotNull @Valid RegistrationInputWebModel registrationInputWebModel);

    @GET
    @Path("/sexes")
    @Produces(MediaType.APPLICATION_JSON)
    List<SexWebModel> getAllSexes();

    @GET
    @Path("/idTypes")
    @Produces(MediaType.APPLICATION_JSON)
    List<IdTypeWebModel> getAllIdTypes();

}
