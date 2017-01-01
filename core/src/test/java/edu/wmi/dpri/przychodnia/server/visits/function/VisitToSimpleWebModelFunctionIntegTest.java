package edu.wmi.dpri.przychodnia.server.visits.function;

import edu.wmi.dpri.przychodnia.commons.visits.webmodel.SimpleVisitWebModel;
import edu.wmi.dpri.przychodnia.server.entity.Visit;
import edu.wmi.dpri.przychodnia.server.integration.rule.DbScriptRule;
import edu.wmi.dpri.przychodnia.server.visits.service.VisitService;
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
 * Created by lupus on 01.01.17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles({"integration", "secure"})
public class VisitToSimpleWebModelFunctionIntegTest {

    public static final long VISIT_ID = 1L;
    public static final String JUSTYN_CZARNECKI = "Justyn Czarnecki";
    public static final String PESEL = "73110572051";
    public static final long PATIENT_ID = 1L;
    @Rule
    @Inject
    public DbScriptRule dbScriptRule;

    @Inject
    private VisitService service;

    @Inject
    private VisitToSimpleWebModelFunction tested;

    @Test
    public void apply() throws Exception {
        // given
        Visit visit = service.findById(VISIT_ID);
        //when
        SimpleVisitWebModel actual = tested.apply(visit);
        // then
        assertThat(actual).isNotNull();
        assertThat(actual.getDoctorId()).isEqualTo(PATIENT_ID);
        assertThat(actual.getVisitId()).isEqualTo(VISIT_ID);
        assertThat(actual.getPatientPesel()).isEqualTo(PESEL);
        assertThat(actual.getPatientName()).isEqualTo(JUSTYN_CZARNECKI);
        assertThat(actual.getStart()).isEqualTo(new DateTime(2016, 12, 13, 13, 15).getMillis());
        assertThat(actual.getEnd()).isEqualTo(new DateTime(2016, 12, 13, 13, 30).getMillis());
    }

}