package edu.wmi.dpri.przychodnia.server.availability.function;

import com.google.common.base.Function;
import edu.wmi.dpri.przychodnia.commons.availability.webmodel.TimeWindowWebModel;
import edu.wmi.dpri.przychodnia.server.entity.TimeWindow;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lupus on 10.01.17.
 */
@Component
public class TimeWindowToWebModelFunction implements Function<TimeWindow, TimeWindowWebModel> {

    public static final String PATTERN = "HH:mm";

    @Override
    public TimeWindowWebModel apply(TimeWindow input) {
        TimeWindowWebModel timeWindowWebModel = new TimeWindowWebModel();
        timeWindowWebModel.setId(input.getId());
        timeWindowWebModel.setStart(input.getStartTime().toString(PATTERN));
        timeWindowWebModel.setEnd(input.getEndTime().toString(PATTERN));
        return timeWindowWebModel;
    }

    public List<TimeWindowWebModel> convertAll(List<TimeWindow> input) {
        return input.stream().map(this::apply).collect(Collectors.toList());
    }
}
