package edu.wmi.dpri.przychodnia.commons.usermanagement.webapi;


import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.IdTypeWebModel;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static edu.wmi.dpri.przychodnia.commons.usermanagement.UserManagementConstants.API_PATH;
import static edu.wmi.dpri.przychodnia.commons.usermanagement.webapi.IdTypeManagementWebApi.BASE_PATH;

/**
 * Created by kmazu on 02.07.2016.
 */
@Path(API_PATH + BASE_PATH)
public interface IdTypeManagementWebApi {

    String BASE_PATH = "/idTypes";

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    IdTypeWebModel getOne(@PathParam("id") Long id);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    IdTypeWebModel createOne(@NotNull @Valid IdTypeWebModel input);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    IdTypeWebModel updateIdType(@NotNull @Valid IdTypeWebModel toUpdate);

    @DELETE
    Response deleteOne(@QueryParam("id") @NotNull Long id);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<IdTypeWebModel> getAll();
}
