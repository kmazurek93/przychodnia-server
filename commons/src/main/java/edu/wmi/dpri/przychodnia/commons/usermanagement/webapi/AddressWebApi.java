package edu.wmi.dpri.przychodnia.commons.usermanagement.webapi;


import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.AddressWebModel;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.creation.AddressCreationWebModel;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static edu.wmi.dpri.przychodnia.commons.usermanagement.UserManagementConstants.API_PATH;
import static edu.wmi.dpri.przychodnia.commons.usermanagement.webapi.AddressWebApi.BASE_PATH;


@Path(API_PATH + BASE_PATH)
public interface AddressWebApi {
    String BASE_PATH = "/addresses";

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    AddressWebModel getAddressById(@PathParam("id") @NotNull Long id);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    AddressWebModel createAddress(@NotNull @Valid AddressCreationWebModel addressCreationWebModel);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    AddressWebModel update(@NotNull @Valid AddressWebModel addressWebModel);

    @DELETE
    Response deleteOne(@NotNull @QueryParam("id") Long id);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<AddressWebModel> getAll();
}
