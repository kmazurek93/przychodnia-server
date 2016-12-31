package edu.wmi.dpri.przychodnia.commons.visits.webapi;

import edu.wmi.dpri.przychodnia.commons.visits.webmodel.*;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;
import java.util.Map;

/**
 * Created by lupus on 25.11.16.
 */
public interface VisitsWebApi {

    String BASE_PATH = "/visits";

    @POST
    @Path("/months")
    List<MonthWebModel> getNextAvailableMonthsForDoctor(AvailableTimeRequestModel model);

    @POST
    @Path("/days")
    List<DayWebModel> getAvailableDaysForDoctorsMonth(AvailableTimeRequestModel model);

    @POST
    @Path("/calendar")
    Map<String, List<SimpleAvailabilityWebModel>> getCalendarForDoctor(AvailableTimeRequestModel model);


}
