package edu.wmi.dpri.przychodnia.server.visits.service;

import edu.wmi.dpri.przychodnia.commons.visits.webmodel.VisitDateChangeModel;
import edu.wmi.dpri.przychodnia.server.entity.Visit;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.auth.ForbiddenException;
import edu.wmi.dpri.przychodnia.server.integration.rule.DbScriptRule;
import edu.wmi.dpri.przychodnia.server.repository.VisitRepository;
import edu.wmi.dpri.przychodnia.server.usermanagement.providers.SampleSecurityContextProvider;
import edu.wmi.dpri.przychodnia.server.usermanagement.service.UserService;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by lupus on 08.01.17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles({"integration", "secure"})
public class VisitDateChangeServiceTest {

    public static final long VISIT_ID = 5L;
    public static final long PATIENT_ID = 2L;
    public static final LocalTime EXPECTED_VISIT_TIME = new LocalTime(9, 30);
    public static final long USER_ID = 3L;
    public static final DateTime FREE_DATE = new DateTime(2016, 12, 14, 9, 30, 0);
    public static final DateTime OCCUPIED_DATE = new DateTime(2016, 12, 14, 8, 30, 0);

    @Rule
    @Inject
    public DbScriptRule rule;

    @Inject
    private VisitDateChangeService tested;

    @Inject
    private VisitRepository repository;
    @Inject
    private UserService userService;
    @Inject
    private SampleSecurityContextProvider sampleSecurityContextProvider;

    @Test
    public void shouldChangeVisitDate() throws Exception {
        //given
        VisitDateChangeModel model = new VisitDateChangeModel();
        model.setVisitId(VISIT_ID);
        model.setNewStartDate(FREE_DATE.getMillis());

        sampleSecurityContextProvider.createSampleContextForUser(USER_ID);

        //when
        Visit visit = tested.changeVisitDate(model);
        //then
        assertThat(visit).isNotNull();
        assertThat(visit.getId()).isNotEqualTo(VISIT_ID);
        assertThat(visit.getPatient().getId()).isEqualTo(PATIENT_ID);
        assertThat(visit.getTimeWindow().getStartTime()).isEqualTo(EXPECTED_VISIT_TIME);
    }

    @Test(expected = ForbiddenException.class)
    public void shouldNotChangeVisitDateIfWindowIsOccupied() {
        //given
        VisitDateChangeModel model = new VisitDateChangeModel();
        model.setVisitId(VISIT_ID);
        model.setNewStartDate(OCCUPIED_DATE.getMillis());

        sampleSecurityContextProvider.createSampleContextForUser(USER_ID);

        //when
        tested.changeVisitDate(model);
        //then exception should be thrown
    }
}