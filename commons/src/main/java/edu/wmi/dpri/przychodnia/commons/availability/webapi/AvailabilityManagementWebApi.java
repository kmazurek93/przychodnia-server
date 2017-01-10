package edu.wmi.dpri.przychodnia.commons.availability.webapi;

import edu.wmi.dpri.przychodnia.commons.availability.webmodel.DoctorAvailabilityWebModel;
import edu.wmi.dpri.przychodnia.commons.availability.webmodel.TimeWindowWebModel;

import javax.ws.rs.*;
import java.util.List;

import static edu.wmi.dpri.przychodnia.commons.availability.webapi.AvailabilityManagementWebApi.BASE_PATH;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by lupus on 10.01.17.
 */
@Path(BASE_PATH)
public interface AvailabilityManagementWebApi {
    String BASE_PATH = "/doctorAvailabilityManagement";
    String TIME_WINDOWS = "/timeWindows";
    String DA = "/da";


    @GET
    @Produces(APPLICATION_JSON)
    @Path(TIME_WINDOWS)
    List<TimeWindowWebModel> getTimeWindows();

    @GET
    @Path("/da/{id}")
    @Produces(APPLICATION_JSON)
    List<DoctorAvailabilityWebModel> getAvailabilitiesForDoctor(@PathParam("id") Long doctorId);

    @POST
    @Path(DA)
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    DoctorAvailabilityWebModel createDoctorAvailabilityWebModel(
            DoctorAvailabilityWebModel model);

    @PUT
    @Path(DA)
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    DoctorAvailabilityWebModel updateDoctorAvailability(
            DoctorAvailabilityWebModel model);

    @DELETE
    @Path("/da/{availabilityId}/{doctorId}")
    void deleteDoctorAvailability(@PathParam("availabilityId") Long availabilityId, @PathParam("doctorId") Long doctorId);

}
