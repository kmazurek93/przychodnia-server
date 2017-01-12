package edu.wmi.dpri.przychodnia.server.visits.web.service;

import edu.wmi.dpri.przychodnia.commons.visits.webmodel.AvailableTimeRequestModel;
import edu.wmi.dpri.przychodnia.commons.visits.webmodel.DayWebModel;
import edu.wmi.dpri.przychodnia.commons.visits.webmodel.MonthWebModel;
import edu.wmi.dpri.przychodnia.server.integration.rule.DbScriptRule;
import edu.wmi.dpri.przychodnia.server.visits.service.NowProvider;
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

    @Inject
    private NowProvider nowProvider;

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
        actual.forEach(o -> {
            if(new DateTime(o.getMonthStart()).isBefore(nowProvider.now())) {
                assertThat(o.getAvailable()).isFalse();
            } else {
                assertThat(o.getAvailable()).isTrue();
            }
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
        actual.forEach(o -> {
            if(new DateTime(o.getDayStart()).isBefore(nowProvider.now())) {
                assertThat(o.getAvailable()).isFalse();
            } else {
                assertThat(o.getAvailable()).isTrue();
            }
        });
    }
}