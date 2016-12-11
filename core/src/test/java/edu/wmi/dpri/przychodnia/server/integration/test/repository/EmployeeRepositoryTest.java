package edu.wmi.dpri.przychodnia.server.integration.test.repository;

import edu.wmi.dpri.przychodnia.server.entity.Employee;
import edu.wmi.dpri.przychodnia.server.integration.rule.DbScriptRule;
import edu.wmi.dpri.przychodnia.server.repository.EmployeeRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by lupus on 11.12.16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles({"integration", "secure"})
public class EmployeeRepositoryTest {

    @Inject
    @Rule
    public DbScriptRule rule;

    @Inject
    private EmployeeRepository tested;

    @Test
    public void findByDoctorId() throws Exception {
        // when
        Employee actual = tested.findByDoctorId(1L);
        // then
        assertThat(actual).isNotNull();

    }

}