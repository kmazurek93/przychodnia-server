package edu.wmi.dpri.przychodnia.server.usermanagement.service;

import edu.wmi.dpri.przychodnia.server.entity.Role;
import edu.wmi.dpri.przychodnia.server.entity.User;
import edu.wmi.dpri.przychodnia.server.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static edu.wmi.dpri.przychodnia.server.security.model.RoleAuthority.*;
import static org.hibernate.Hibernate.initialize;

/**
 * Created by lupus on 18.10.16.
 */
@Service
public class RoleService {

    @Inject
    private RoleRepository roleRepository;
    @Inject
    private RoleAndDbRelationService roleAndDbRelationService;

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
    public List<Role> findAllUnInitialized() {
        return newArrayList(roleRepository.findAll());
    }

    @Transactional
    public void assignRole(User user, Role role) {
        String name = role.getName();
        switch (name) {
            case ROLE_STAFF:
            case ROLE_ADMIN:
                roleAndDbRelationService.addAsEmployee(user);
                break;
            case ROLE_PATIENT:
                roleAndDbRelationService.addAsPatient(user);
                break;
            case ROLE_DOCTOR:
                roleAndDbRelationService.addAsEmployee(user);
                roleAndDbRelationService.addAsDoctor(user);
                break;
        }
        role.getUsers().add(user);
        roleRepository.save(role);
    }

    @Transactional
    public void unassignRole(User user, Role role) {
        role.getUsers().removeIf(u -> u.getId().equals(user.getId()));
        roleRepository.save(role);
    }

    @Transactional
    public void editRoles(User user, List<Role> roles) {
        List<Role> userRoles = user.getRoles();
        for(Role ur : userRoles)
        {
            if(!roles.contains(ur)) {
                unassignRole(user, ur);
            }
        }

        for(Role r : roles)
        {
            if(!userRoles.contains(r))
            {
                assignRole(user, r);
            }
        }
    }
}
