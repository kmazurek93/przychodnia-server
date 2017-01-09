package edu.wmi.dpri.przychodnia.server.visits.service;

import edu.wmi.dpri.przychodnia.commons.visits.enums.VisitStatusType;
import edu.wmi.dpri.przychodnia.commons.visits.webmodel.FullVisitWebModel;
import edu.wmi.dpri.przychodnia.commons.visits.webmodel.PatientHistoryQueryModel;
import edu.wmi.dpri.przychodnia.commons.visits.webmodel.VisitQueryModel;
import edu.wmi.dpri.przychodnia.server.entity.Visit;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.auth.ForbiddenException;
import edu.wmi.dpri.przychodnia.server.integration.rule.DbScriptRule;
import edu.wmi.dpri.przychodnia.server.usermanagement.providers.SampleSecurityContextProvider;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by lupus on 01.01.17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles({"integration", "secure"})
public class VisitServiceIntegTest {

    public static final long DOCTOR_ID = 1L;
    public static final long PATIENT_ID = 2L;
    public static final String PESEL = "73110572051";
    public static final long USER_ID = 3L;
    public static final long VISIT_ID = 5L;
    public static final long DOCTOR_ID1 = 1L;
    @Rule
    @Inject
    public DbScriptRule dbScriptRule;

    @Inject
    private VisitService tested;
    @Inject
    private SampleSecurityContextProvider securityContextProvider;
    @Before
    public void before() {
        try {
            dbScriptRule.before();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }


    @Test
    public void getOwnDoctorVisits() throws Exception {
        //given
        VisitQueryModel model = getVisitQueryModel();
        //when
        Page<Visit> actual = tested.getOwnDoctorVisits(model);
        //then
        assertThat(actual.getContent()).hasSize(4);

    }

    @Test
    public void getOwnPatientVisits() throws Exception {
        //given
        VisitQueryModel model = getVisitQueryModel();
        //when
        Page<Visit> actual = tested.getOwnPatientVisits(model);
        //then
        List<Visit> content = actual.getContent();
        assertThat(content).isNotEmpty();
        assertThat(content.get(0).getTimeWindow().getId()).isEqualTo(22L);
        assertThat(content.get(1).getTimeWindow().getId()).isEqualTo(1L);
        assertThat(content.get(2).getTimeWindow().getId()).isEqualTo(3L);
        assertThat(content).hasSize(3);
    }

    private VisitQueryModel getVisitQueryModel() {
        VisitQueryModel model = new VisitQueryModel();
        model.setFrom(new DateTime(2016, 12, 1, 0, 0).getMillis());
        model.setStatus(VisitStatusType.HAPPENED);
        model.setDoctorId(DOCTOR_ID);
        model.setPatientId(PATIENT_ID);
        model.setPage(0);
        model.setSize(100);
        return model;
    }

    @Test
    public void shouldGetPatientHistory() {
        PatientHistoryQueryModel model = new PatientHistoryQueryModel();
        model.setPage(0);
        model.setSize(100);
        model.setPesel(PESEL);
        //when
        Page<Visit> actual = tested.getPatientHistory(model);
        //then
        assertThat(actual).isNotNull();
        assertThat(actual.getContent()).hasSize(3);
    }
    @Test(expected = ForbiddenException.class)
    public void shouldNotAllowVisitModificationIfNotDoctorAndNotStaff() {
        securityContextProvider.createSampleContextForUser(USER_ID);
        FullVisitWebModel model = new FullVisitWebModel();
        model.setDoctorId(DOCTOR_ID1);
        model.setComment("123");
        model.setVisitId(VISIT_ID);
        // when
        tested.updateVisit(model, false);
        //then exception
    }

}