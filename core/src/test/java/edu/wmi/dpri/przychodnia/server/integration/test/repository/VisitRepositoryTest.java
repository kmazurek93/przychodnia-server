package edu.wmi.dpri.przychodnia.server.integration.test.repository;

import edu.wmi.dpri.przychodnia.server.integration.rule.DbScriptRule;
import edu.wmi.dpri.przychodnia.server.repository.AvailabilityRepository;
import edu.wmi.dpri.przychodnia.server.repository.VisitRepository;
import edu.wmi.dpri.przychodnia.server.visits.service.NowProvider;
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
public class VisitRepositoryTest {

    public static final long DOCTOR_ID = 1L;
    public static final DateTime END_DATE = new DateTime(2016, 12, 31, 23, 59);
    @Inject
    private VisitRepository tested;
    @Inject
    private NowProvider nowProvider;

    @Inject
    private AvailabilityRepository availabilityRepository;

    @Inject
    @Rule
    public DbScriptRule rule;

    @Test
    public void shouldCountVisitsProperly() {
        //when
        Long actual = tested.countByDoctorIdAndDateBetween(DOCTOR_ID, nowProvider.now(), END_DATE);
        // then
        assertThat(actual).isEqualTo(1L);
    }


}
