package edu.wmi.dpri.przychodnia.commons.visits.webapi;

import edu.wmi.dpri.przychodnia.commons.visits.webmodel.AvailableTimeRequestModel;
import edu.wmi.dpri.przychodnia.commons.visits.webmodel.DayWebModel;
import edu.wmi.dpri.przychodnia.commons.visits.webmodel.MonthWebModel;
import edu.wmi.dpri.przychodnia.commons.visits.webmodel.VisitWebModel;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;

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
    @Path("/day")
    List<VisitWebModel> getVisitsOfDoctor(AvailableTimeRequestModel model);

//    @POST

}
