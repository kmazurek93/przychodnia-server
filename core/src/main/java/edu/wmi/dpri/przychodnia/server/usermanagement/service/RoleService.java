package edu.wmi.dpri.przychodnia.server.usermanagement.service;

import edu.wmi.dpri.przychodnia.server.entity.Role;
import edu.wmi.dpri.przychodnia.server.repository.RoleRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by lupus on 18.10.16.
 */
@Component
public class RoleService {

    @Inject
    private RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    public List<Role> findByNameIn(List<String> names) {
        return roleRepository.findByNameIn(names);
    }
}
