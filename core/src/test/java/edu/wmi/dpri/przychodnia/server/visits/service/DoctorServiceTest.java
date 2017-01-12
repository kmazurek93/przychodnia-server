package edu.wmi.dpri.przychodnia.server.visits.service;

import edu.wmi.dpri.przychodnia.server.entity.Doctor;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.NotFoundException;
import edu.wmi.dpri.przychodnia.server.integration.rule.DbScriptRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles({"integration", "secure"})
public class DoctorServiceTest {

    public static final long DOCTOR_ID = 1L;
    public static final long NON_EXISITNG_DOCTOR_ID = 1235465L;
    @Rule
    @Inject
    public DbScriptRule dbScriptRule;

    @Inject
    private DoctorService tested;


    @Test
    public void findById() throws Exception {
        //when
        Doctor actual = tested.findById(DOCTOR_ID);
        //then
        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(DOCTOR_ID);
    }


    @Test(expected = NotFoundException.class)
    public void shouldThrowExceptionIfDoctorDoesNotExist() {
        //when
        tested.findById(NON_EXISITNG_DOCTOR_ID);
        //then exception should be thrown
    }
}