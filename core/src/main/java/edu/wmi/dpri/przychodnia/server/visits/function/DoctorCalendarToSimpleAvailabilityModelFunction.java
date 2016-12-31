package edu.wmi.dpri.przychodnia.server.visits.function;

import com.google.common.base.Function;
import edu.wmi.dpri.przychodnia.commons.visits.webmodel.AvailabilityType;
import edu.wmi.dpri.przychodnia.commons.visits.webmodel.SimpleAvailabilityWebModel;
import edu.wmi.dpri.przychodnia.server.entity.procedures.DoctorCalendar;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lupus on 31.12.16.
 */
@Component
public class DoctorCalendarToSimpleAvailabilityModelFunction
        implements Function<DoctorCalendar, SimpleAvailabilityWebModel> {

    public static final String HOUR_PATTERN = "hh:mm";
    public static final String DAY_START = "00:00";
    public static final String DAY_END = "23:59";

    @Override
    public SimpleAvailabilityWebModel apply(DoctorCalendar input) {
        SimpleAvailabilityWebModel outcome = new SimpleAvailabilityWebModel();
        outcome.setTimeEnd(determineEndTimeString(input));
        outcome.setTimeStart(determineStartTimeString(input));
        outcome.setDate(input.getDate().toString());
        outcome.setOrder(determineOrder(input));
        outcome.setVisitId(input.getVisit_id());
        outcome.setStatus(input.getState());
        outcome.setType(determineType(input));
        return outcome;
    }

    public List<SimpleAvailabilityWebModel> convertAll(List<DoctorCalendar> inputs) {
        return inputs.stream().map(this::apply).collect(Collectors.toList());
    }

    private int determineOrder(DoctorCalendar input) {
        return input.getOrder() != null ? input.getOrder() : 0;
    }

    private String determineStartTimeString(DoctorCalendar input) {
        return input.getStartTime() != null ? input.getStartTime().toString(HOUR_PATTERN) : DAY_START;
    }

    private String determineEndTimeString(DoctorCalendar input) {
        return input.getEndTime() != null ? input.getEndTime().toString(HOUR_PATTERN) : DAY_END;
    }

    private String determineType(DoctorCalendar input) {
        if (input.getStartTime() == null) {
            return AvailabilityType.DAY;
        }
        return AvailabilityType.TIME_WINDOW;
    }

}
