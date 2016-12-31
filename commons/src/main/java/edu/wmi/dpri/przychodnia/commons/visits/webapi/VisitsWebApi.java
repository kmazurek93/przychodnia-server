package edu.wmi.dpri.przychodnia.commons.visits.webapi;

import edu.wmi.dpri.przychodnia.commons.visits.webmodel.AvailableTimeRequestModel;
import edu.wmi.dpri.przychodnia.commons.visits.webmodel.DayWebModel;
import edu.wmi.dpri.przychodnia.commons.visits.webmodel.MonthWebModel;
import edu.wmi.dpri.przychodnia.commons.visits.webmodel.SimpleAvailabilityWebModel;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
    List<MonthWebModel> getNextAvailableMonthsForDoctor(AvailableTimeRequestModel model);

    @POST
    @Path(DAYS)
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    List<DayWebModel> getAvailableDaysForDoctorsMonth(AvailableTimeRequestModel model);

    @POST
    @Path(CALENDAR)
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    Map<String, List<SimpleAvailabilityWebModel>> getCalendarForDoctor(AvailableTimeRequestModel model);


}
