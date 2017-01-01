package edu.wmi.dpri.przychodnia.server.visits.service;

import edu.wmi.dpri.przychodnia.commons.visits.enums.VisitStatusType;
import edu.wmi.dpri.przychodnia.commons.visits.webmodel.VisitQueryModel;
import edu.wmi.dpri.przychodnia.server.entity.Visit;
import edu.wmi.dpri.przychodnia.server.integration.rule.DbScriptRule;
import org.joda.time.DateTime;
import org.junit.Ignore;
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
 * Created by lupus on 01.01.17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles({"integration", "secure"})
public class VisitServiceIntegTest {

    public static final long DOCTOR_ID = 1L;
    public static final long PATIENT_ID = 2L;
    @Rule
    @Inject
    public DbScriptRule dbScriptRule;

    @Inject
    private VisitService tested;

    @Test
    @Ignore
    public void countVisitsByDoctorOnMonth() throws Exception {

    }

    @Test
    public void getOwnDoctorVisits() throws Exception {
        //given
        VisitQueryModel model = getVisitQueryModel();
        //when
        List<Visit> actual = tested.getOwnDoctorVisits(model);
        //then
        assertThat(actual).hasSize(5);

    }

    @Test
    public void getOwnPatientVisits() throws Exception {
        //given
        VisitQueryModel model = getVisitQueryModel();
        //when
        List<Visit> actual = tested.getOwnPatientVisits(model);
        //then
        assertThat(actual).isNotEmpty();
        assertThat(actual.get(0).getTimeWindow().getId()).isEqualTo(22L);
        assertThat(actual.get(1).getTimeWindow().getId()).isEqualTo(1L);
        assertThat(actual.get(2).getTimeWindow().getId()).isEqualTo(2L);
        assertThat(actual.get(3).getTimeWindow().getId()).isEqualTo(3L);
        assertThat(actual).hasSize(4);
    }

    private VisitQueryModel getVisitQueryModel() {
        VisitQueryModel model = new VisitQueryModel();
        model.setFrom(new DateTime(2016, 12, 1, 0, 0).getMillis());
        model.setStatus(VisitStatusType.HAPPENED);
        model.setDoctorId(DOCTOR_ID);
        model.setPatientId(PATIENT_ID);
        return model;
    }

}