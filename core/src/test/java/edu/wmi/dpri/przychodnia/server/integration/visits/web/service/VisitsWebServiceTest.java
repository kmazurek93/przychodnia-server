package edu.wmi.dpri.przychodnia.server.integration.visits.web.service;

import edu.wmi.dpri.przychodnia.commons.visits.webmodel.AvailableTimeRequestModel;
import edu.wmi.dpri.przychodnia.commons.visits.webmodel.MonthWebModel;
import edu.wmi.dpri.przychodnia.server.integration.rule.DbScriptRule;
import edu.wmi.dpri.przychodnia.server.visits.web.service.VisitsWebService;
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
public class VisitsWebServiceTest {

    public static final long DOCTOR_ID = 1L;
    @Inject
    @Rule
    public DbScriptRule dbScriptRule;

    @Inject
    private VisitsWebService tested;


    @Test
    public void getNextAvailableMonthsForDoctor() throws Exception {
        //given
        AvailableTimeRequestModel model = new AvailableTimeRequestModel();
        model.setDate(new DateTime(2016, 7, 1, 0, 0).getMillis());
        model.setDoctorId(DOCTOR_ID);
        //when
        List<MonthWebModel> actual = tested.getNextAvailableMonthsForDoctor(model);
        // then
        assertThat(actual).hasSize(6);
        actual.forEach(o -> assertThat(o.getAvailable()).isTrue());
    }

}