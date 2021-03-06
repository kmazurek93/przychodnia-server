package edu.wmi.dpri.przychodnia.server.entity.enums;

import org.junit.Test;

import static edu.wmi.dpri.przychodnia.server.entity.enums.WeekdayType.MONDAY;
import static org.assertj.core.api.Assertions.assertThat;


public class WeekdayTypeTest {


    @Test
    public void fromInt() throws Exception {
        WeekdayType weekdayType = WeekdayType.fromInt(1);
        assertThat(weekdayType).isEqualTo(MONDAY);
    }

}