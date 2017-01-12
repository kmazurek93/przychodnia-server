package edu.wmi.dpri.przychodnia.server.visits.service;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

import static java.lang.System.out;
import static org.assertj.core.api.Assertions.assertThat;



public class LocalTimeLocalDateConcatenationTest {

    public static final String DATE = "2016-12-31";
    public static final String TIME = "08:30";
    public static final String PATTERN = "HH:mm";

    @Test
    public void testTimeDate() {
        LocalDate localDate = new LocalDate(DATE);
        LocalTime localTime = new LocalTime(TIME);
        String actual = localDate.toString() + " " + localTime.toString(PATTERN);
        String expected = DATE + " " + TIME;
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    public void testDateTime() {
        DateTime dateTime = new DateTime(DATE);
        out.println(dateTime.toString());
    }
}
