package edu.wmi.dpri.przychodnia.server.availability.function;

import com.google.common.base.Function;
import edu.wmi.dpri.przychodnia.commons.availability.webmodel.DoctorAvailabilityWebModel;
import edu.wmi.dpri.przychodnia.server.entity.Availability;
import edu.wmi.dpri.przychodnia.server.entity.enums.WeekdayType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lupus on 10.01.17.
 */
@Component
public class AvailabilityToWebModelFunction implements Function<Availability, DoctorAvailabilityWebModel> {

    @Override
    public DoctorAvailabilityWebModel apply(Availability input) {
        DoctorAvailabilityWebModel output = new DoctorAvailabilityWebModel();
        output.setAvailabilityId(input.getId());
        output.setTimeWindowStartId(input.getStartTimeWindow().getId());
        output.setTimeWindowEndId(input.getEndTimeWindow().getId());
        output.setValidFrom(input.getDateStart().getMillis());
        output.setValidTo(input.getDateEnd().getMillis());
        output.setWeekday(WeekdayType.fromInt(input.getWeekday()).toString());
        return output;
    }

    public List<DoctorAvailabilityWebModel> convertAllWithDoctorId(List<Availability> input, Long doctorId) {
        List<DoctorAvailabilityWebModel> models = input.stream().map(this::apply).collect(Collectors.toList());
        models.forEach(o -> o.setDoctorId(doctorId));
        return models;
    }
}
