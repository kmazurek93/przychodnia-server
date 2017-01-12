package edu.wmi.dpri.przychodnia.server.visits.function;

import edu.wmi.dpri.przychodnia.commons.visits.webmodel.SimpleAvailabilityWebModel;
import edu.wmi.dpri.przychodnia.server.entity.procedures.DoctorCalendar;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

import static edu.wmi.dpri.przychodnia.commons.visits.webmodel.AvailabilityStatus.FREE;
import static edu.wmi.dpri.przychodnia.commons.visits.webmodel.AvailabilityStatus.UNAVAILABLE;
import static edu.wmi.dpri.przychodnia.commons.visits.webmodel.AvailabilityType.DAY;
import static edu.wmi.dpri.przychodnia.commons.visits.webmodel.AvailabilityType.TIME_WINDOW;
import static org.assertj.core.api.Assertions.assertThat;


public class DoctorCalendarToSimpleAvailabilityModelFunctionTest {

    public static final String DATE_STRING = "2016-01-01";
    public static final LocalDate DATE = new LocalDate(DATE_STRING);
    public static final String HOUR_START_STRING = "11:30";
    public static final LocalTime START_TIME = new LocalTime(HOUR_START_STRING);
    public static final String HOUR_END_STRING = "11:45";
    public static final LocalTime END_TIME = new LocalTime(HOUR_END_STRING);
    public static final int ORDER = 7;
    public static final String DAY_START = "00:00";
    public static final String DAY_END = "23:59";
    private DoctorCalendarToSimpleAvailabilityModelFunction tested =
            new DoctorCalendarToSimpleAvailabilityModelFunction();

    @Test
    public void shouldConvertToModelWithTimeWindowType() throws Exception {
        //given
        DoctorCalendar input = new DoctorCalendar();
        input.setDate(DATE);
        input.setStartTime(START_TIME);
        input.setEndTime(END_TIME);
        input.setVisit_id(null);
        input.setState(FREE);
        input.setOrder(ORDER);
        //when
        SimpleAvailabilityWebModel actual = tested.apply(input);
        // then
        assertThat(actual).isNotNull();
        assertThat(actual.getTimeStart()).isEqualTo(HOUR_START_STRING);
        assertThat(actual.getTimeEnd()).isEqualTo(HOUR_END_STRING);
        assertThat(actual.getDate()).isEqualTo(DATE_STRING);
        assertThat(actual.getType()).isEqualTo(TIME_WINDOW);
        assertThat(actual.getStatus()).isEqualTo(FREE);
        assertThat(actual.getVisitId()).isNull();
    }

    @Test
    public void shouldConvertToModelWithDayType() {
        //given
        DoctorCalendar input = new DoctorCalendar();
        input.setDate(DATE);
        input.setStartTime(null);
        input.setEndTime(null);
        input.setVisit_id(null);
        input.setState(UNAVAILABLE);
        input.setOrder(null);
        //when
        SimpleAvailabilityWebModel actual = tested.apply(input);
        // then
        assertThat(actual).isNotNull();
        assertThat(actual.getTimeStart()).isEqualTo(DAY_START);
        assertThat(actual.getTimeEnd()).isEqualTo(DAY_END);
        assertThat(actual.getDate()).isEqualTo(DATE_STRING);
        assertThat(actual.getType()).isEqualTo(DAY);
        assertThat(actual.getStatus()).isEqualTo(UNAVAILABLE);
        assertThat(actual.getVisitId()).isNull();
    }

}