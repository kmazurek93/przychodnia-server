package edu.wmi.dpri.przychodnia.server.visits.web.service;

import edu.wmi.dpri.przychodnia.commons.visits.webmodel.CalendarRequestModel;
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
import java.util.Map;
import java.util.Set;

import static edu.wmi.dpri.przychodnia.server.visits.service.DoctorCalendarServiceIntegrationTest.END_DATE;
import static edu.wmi.dpri.przychodnia.server.visits.service.DoctorCalendarServiceIntegrationTest.START_DATE;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by lupus on 31.12.16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles({"integration", "secure"})
public class DoctorCalendarWebServiceIntegTest {

    @Rule
    @Inject
    public DbScriptRule rule;

    @Inject
    private DoctorCalendarWebService tested;

    @Test
    public void getDoctorCalendar() throws Exception {
        //given
        CalendarRequestModel model = new CalendarRequestModel();
        model.setDoctorId(1L);
        model.setStartDate(new DateTime(START_DATE).getMillis());
        model.setEndDate(new DateTime(END_DATE).getMillis());
        //when
        Map<String, List<SimpleAvailabilityWebModel>> actual = tested.getDoctorCalendar(model);
        //then
        Set<String> keySet = actual.keySet();
        assertThat(keySet).hasSize(31);
        keySet.forEach(key -> assertThat(actual.get(key)).isNotEmpty());
    }

}