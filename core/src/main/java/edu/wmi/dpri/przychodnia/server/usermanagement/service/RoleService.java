package edu.wmi.dpri.przychodnia.server.usermanagement.service;

import edu.wmi.dpri.przychodnia.server.entity.Role;
import edu.wmi.dpri.przychodnia.server.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

import static org.hibernate.Hibernate.initialize;

/**
 * Created by lupus on 18.10.16.
 */
@Service
public class RoleService {

    @Inject
    private RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    public List<Role> findByNameIn(List<String> names) {

        List<Role> roles = roleRepository.findByNameIn(names);
        roles.forEach(role -> initialize(role.getUsers()));
        return roles;
    }


    @Transactional(readOnly = true)
    public List<Role> queryLikeName(String name) {
        return roleRepository.findByNameLike(name);
    }
}
