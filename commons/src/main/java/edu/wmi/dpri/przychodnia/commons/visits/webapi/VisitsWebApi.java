package edu.wmi.dpri.przychodnia.commons.visits.webapi;

import edu.wmi.dpri.przychodnia.commons.visits.webmodel.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import java.util.List;
import java.util.Map;

import static edu.wmi.dpri.przychodnia.commons.visits.webapi.VisitsWebApi.BASE_PATH;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by lupus on 25.11.16.
 */
@Path(BASE_PATH)
public interface VisitsWebApi {

    String BASE_PATH = "/visits";
    String MONTHS = "/months";
    String DAYS = "/days";
    String CALENDAR = "/calendar";

    @POST
    @Path(MONTHS)
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    List<MonthWebModel> getNextAvailableMonthsForDoctor(@NotNull @Valid AvailableTimeRequestModel model);

    @POST
    @Path(DAYS)
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    List<DayWebModel> getAvailableDaysForDoctorsMonth(@NotNull @Valid AvailableTimeRequestModel model);

    @POST
    @Path(CALENDAR)
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    Map<String, List<SimpleAvailabilityWebModel>> getCalendarForDoctor(@NotNull @Valid CalendarRequestModel model);

    @POST
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    SimpleVisitWebModel createNewVisit(SimpleVisitWebModel visitRequest);

    //TODO accept/decline /email should be sent/
    //TODO not sure if should be used
    @PUT
    @Path("/status/{id}")
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    SimpleVisitWebModel alterVisitStatus(@PathParam("id") @NotNull Long visitId, VisitStatusChangeModel visitStatusChangeModel);

    @POST
    @Path("/my")
    @Produces(APPLICATION_JSON)
    List<SimpleVisitWebModel> getAllOwnVisits(VisitQueryModel model);

    //todo get visit details
    @GET
    @Path("/{id}")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    FullVisitWebModel getVisitDetails(@PathParam("id") @NotNull Long visitId);

    //todo edit visit - doctor only.
    @PUT
    @Path("/{id}")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    FullVisitWebModel alterVisit(@PathParam("id") @NotNull Long visitId, @NotNull FullVisitWebModel fullVisitWebModel);

    //TODO changeDate
    @PUT
    @Path("/changeDate")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    SimpleVisitWebModel changeVisitDate(@NotNull @Valid VisitDateChangeModel model);

    //TODO remove
    @DELETE
    @Path("/{id}")
    void removeVisit(@PathParam("id") Long id);




}
