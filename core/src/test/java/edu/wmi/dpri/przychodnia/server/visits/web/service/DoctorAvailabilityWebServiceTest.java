package edu.wmi.dpri.przychodnia.server.visits.web.service;

import edu.wmi.dpri.przychodnia.commons.visits.webmodel.AvailableTimeRequestModel;
import edu.wmi.dpri.przychodnia.commons.visits.webmodel.DayWebModel;
import edu.wmi.dpri.przychodnia.commons.visits.webmodel.MonthWebModel;
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
 * Created by lupus on 18.12.16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles({"integration", "secure"})
public class DoctorAvailabilityWebServiceTest {

    public static final long DOCTOR_ID = 1L;
    public static final DateTime DATE_TIME = new DateTime(2016, 7, 1, 0, 0);
    @Inject
    @Rule
    public DbScriptRule dbScriptRule;

    @Inject
    private DoctorAvailabilityWebService tested;


    @Test
    public void getNextAvailableMonthsForDoctor() throws Exception {
        //given
        AvailableTimeRequestModel model = new AvailableTimeRequestModel();
        model.setStartDate(DATE_TIME.getMillis());
        model.setDoctorId(DOCTOR_ID);
        //when
        List<MonthWebModel> actual = tested.getNextAvailableMonthsForDoctor(model);
        // then
        assertThat(actual).hasSize(6);
        actual.forEach(o -> assertThat(o.getAvailable()).isTrue());
        actual.forEach(o -> {
            DateTime dateTime = new DateTime(o.getMonthStart());
            assertThat(dateTime.getDayOfMonth()).isEqualTo(1);
        });
    }

    @Test
    public void getDayAvailabilityForDoctorAndMonth() throws Exception {
        // given
        AvailableTimeRequestModel model = new AvailableTimeRequestModel();
        model.setStartDate(DATE_TIME.getMillis());
        model.setDoctorId(DOCTOR_ID);
        // when
        List<DayWebModel> actual = tested.getDayAvailabilityForDoctorAndMonth(model);
        // then
        assertThat(actual).hasSize(31);
        actual.forEach(o -> assertThat(o.getAvailable()).isTrue());
        for (int i = 0; i < 31; i++) {
            DateTime day = new DateTime(actual.get(i).getDayStart());
            assertThat(day.getDayOfMonth()).isEqualTo(i + 1);
        }
    }
}