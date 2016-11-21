package edu.wmi.dpri.przychodnia.server.usermanagement.internal.integ;

import edu.wmi.dpri.przychodnia.server.entity.Role;
import edu.wmi.dpri.przychodnia.server.repository.AddressRepository;
import edu.wmi.dpri.przychodnia.server.repository.RoleRepository;
import edu.wmi.dpri.przychodnia.server.repository.UserRepository;
import edu.wmi.dpri.przychodnia.server.usermanagement.service.AddressService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by lupus on 19.11.16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles({"local", "secure"})
@Ignore("used only for internal testing")
public class InternalIntegTest {

    @Inject
    private RoleRepository roleRepository;

    @Inject
    private AddressRepository addressRepository;
    @Inject
    private AddressService addressService;
    @Inject
    private UserRepository userRepository;
    @Test
    public void shit() {
        List<Role> roles = roleRepository.findByNameLike("%adm%");
        assertThat(roles).hasSize(1);
        assertThat(roles.get(0).getUsers().get(0).getLogin()).isEqualToIgnoringCase("admin");
        assertThat(roles.get(0).getUsers().get(0).getPerson().getFirstName()).isEqualToIgnoringCase("super");

    }

}
