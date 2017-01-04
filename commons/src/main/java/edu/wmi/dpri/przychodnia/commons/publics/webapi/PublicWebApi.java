package edu.wmi.dpri.przychodnia.commons.publics.webapi;

import edu.wmi.dpri.przychodnia.commons.common.webmodel.PageRequestModel;
import edu.wmi.dpri.przychodnia.commons.common.webmodel.SimplePage;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.IdTypeWebModel;
import edu.wmi.dpri.przychodnia.commons.common.webmodel.NewsWebModel;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.RegistrationOutput;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.SexWebModel;
import edu.wmi.dpri.przychodnia.commons.publics.webmodel.RegistrationInputWebModel;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import java.util.List;

import static edu.wmi.dpri.przychodnia.commons.publics.webapi.PublicWebApi.BASE_PATH;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by kmazu on 02.07.2016.
 */
@Path(BASE_PATH)
public interface PublicWebApi {
    String BASE_PATH = "/public";
    String REGISTRATION = "/registration";
    String ID_TYPES = "/idTypes";
    String SEXES = "/sexes";
    String NEWS = "/news";

    @POST
    @Path(REGISTRATION)
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    RegistrationOutput registerUser(@NotNull @Valid RegistrationInputWebModel registrationInputWebModel);

    @GET
    @Path(SEXES)
    @Produces(APPLICATION_JSON)
    List<SexWebModel> getAllSexes();

    @GET
    @Path(ID_TYPES)
    @Produces(APPLICATION_JSON)
    List<IdTypeWebModel> getAllIdTypes();

    @POST
    @Path(NEWS)
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    SimplePage<NewsWebModel> getNewsPage(PageRequestModel p);

}
