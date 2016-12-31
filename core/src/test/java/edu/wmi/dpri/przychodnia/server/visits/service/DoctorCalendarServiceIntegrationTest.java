package edu.wmi.dpri.przychodnia.server.visits.service;

import edu.wmi.dpri.przychodnia.commons.visits.webmodel.AvailableTimeRequestModel;
import edu.wmi.dpri.przychodnia.commons.visits.webmodel.SimpleAvailabilityWebModel;
import edu.wmi.dpri.przychodnia.server.integration.rule.DbScriptRule;
import org.joda.time.DateTime;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by lupus on 31.12.16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles({"integration", "secure"})
public class DoctorCalendarServiceIntegrationTest {

    public static final String START_DATE = "2016-12-01";
    public static final String END_DATE = "2016-12-31";
    public static final int EXPECTED_SIZE = 361;
    @Rule
    @Inject
    public DbScriptRule rule;

    @Inject
    private DoctorCalendarService tested;

    @Test
    public void getDocotrsCalendar() throws Exception {
        //given
        AvailableTimeRequestModel model = new AvailableTimeRequestModel();
        model.setDoctorId(1L);
        model.setStartDate(new DateTime(START_DATE).getMillis());
        model.setEndDate(new DateTime(END_DATE).getMillis());
        //when
        List<SimpleAvailabilityWebModel> actual = tested.getDoctorsCalendar(model);
        // then
        assertThat(actual).hasSize(EXPECTED_SIZE);
    }

}