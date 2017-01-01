package edu.wmi.dpri.przychodnia.server.visits.function;

import com.google.common.base.Function;
import edu.wmi.dpri.przychodnia.commons.visits.webmodel.SimpleVisitWebModel;
import edu.wmi.dpri.przychodnia.server.entity.Person;
import edu.wmi.dpri.przychodnia.server.entity.Visit;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lupus on 31.12.16.
 */
@Component
public class VisitToSimpleWebModelFunction implements Function<Visit, SimpleVisitWebModel> {


    @Override
    public SimpleVisitWebModel apply(Visit input) {
        SimpleVisitWebModel outcome = new SimpleVisitWebModel();
        outcome.setDoctorId(input.getDoctor().getId());
        outcome.setStatus(input.getStatus());
        outcome.setPatientName(createName(input));
        outcome.setPatientPesel(input.getPatient().getPerson().getPESEL());
        outcome.setVisitId(input.getId());
        fillDates(input, outcome);
        return outcome;
    }

    private void fillDates(Visit input, SimpleVisitWebModel outcome) {
        LocalDate date = input.getDate();
        DateTime dateTime = new DateTime(date.getYear(), date.getMonthOfYear(), date.getDayOfMonth(), 0, 0);
        LocalTime endTime = input.getTimeWindow().getEndTime();
        LocalTime startTime = input.getTimeWindow().getStartTime();
        DateTime endDate = dateTime.withHourOfDay(endTime.getHourOfDay()).withMinuteOfHour(endTime.getMinuteOfHour());
        DateTime startDate = dateTime.withHourOfDay(startTime.getHourOfDay()).withMinuteOfHour(startTime.getMinuteOfHour());
        outcome.setStart(startDate.getMillis());
        outcome.setEnd(endDate.getMillis());
    }

    private String createName(Visit input) {
        Person person = input.getPatient().getPerson();
        return StringUtils.join(person.getFirstName(), " ", person.getLastName());
    }

    public List<SimpleVisitWebModel> convertAll(List<Visit> visits) {
        return visits.stream().map(this::apply).collect(Collectors.toList());
    }
}
