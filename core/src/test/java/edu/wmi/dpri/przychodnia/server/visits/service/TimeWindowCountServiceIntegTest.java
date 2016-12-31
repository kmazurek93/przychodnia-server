package edu.wmi.dpri.przychodnia.server.visits.service;

import edu.wmi.dpri.przychodnia.server.integration.rule.DbScriptRule;
import org.joda.time.DateTime;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by lupus on 18.12.16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles({"integration", "secure"})
public class TimeWindowCountServiceIntegTest {

    public static final long DOCTOR_ID = 1L;
    public static final DateTime DAY = new DateTime(2016, 12, 13, 14, 59);
    @Inject
    @Rule
    public DbScriptRule rule;

    @Inject
    private TimeWindowCountService tested;

    @Test
    public void getMaximumVisitCountForDoctorAndDay() throws Exception {
        //when
        Long actual = tested.getMaximumVisitCountForDoctorAndDay(DOCTOR_ID, DAY);
        //then
        assertThat(actual).isEqualTo(16L);
    }

    @Test
    public void getMaximumVisitCountForDoctorAndMonth() throws Exception {
        // when
        Long actual = tested.getMaximumVisitCountForDoctorAndMonth(DOCTOR_ID, DAY);
        //then
        assertThat(actual).isEqualTo(352L);
    }

}