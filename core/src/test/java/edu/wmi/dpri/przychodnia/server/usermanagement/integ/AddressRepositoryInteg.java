package edu.wmi.dpri.przychodnia.server.usermanagement.integ;

import edu.wmi.dpri.przychodnia.server.repository.AddressRepository;
import edu.wmi.dpri.przychodnia.server.repository.RoleRepository;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

/**
 * Created by lupus on 19.11.16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles({"local", "secure"})
@Ignore("not impl. yet")
public class AddressRepositoryInteg {

    @Inject
    private RoleRepository roleRepository;

    @Inject
    private AddressRepository addressRepository;


}
