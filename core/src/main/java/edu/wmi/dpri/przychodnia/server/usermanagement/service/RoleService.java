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
        Role roleFromRepo = roleRepository.findOne(role.getId());
        roleFromRepo.getUsers().add(user);
        roleRepository.save(roleFromRepo);
    }

    @Transactional
    public void unassignRole(User user, Role role) {
        Role roleFromRepo = roleRepository.findOne(role.getId());
        String name = roleFromRepo.getName();
        switch (name) {
            case ROLE_ADMIN:
                roleAndDbRelationService.removeAdminPrivileges(user);
                break;
            case ROLE_STAFF:
                roleAndDbRelationService.removeStaffPrivileges(user);
                break;
            case ROLE_PATIENT:
                roleAndDbRelationService.removePatientPrivileges(user);
                break;
            case ROLE_DOCTOR:
                roleAndDbRelationService.removeDoctorPrivileges(user);
                break;
            default:
                return;
        }
        roleFromRepo.getUsers().removeIf(u -> u.getId().equals(user.getId()));
        roleRepository.save(roleFromRepo);
    }

    @Transactional
    public void editRoles(User user, List<Role> rolesToAssign) {
        List<Role> allRoles = newArrayList(roleRepository.findAll());
        List<Role> rolesToUnassign = newArrayList(allRoles);
        rolesToUnassign.removeAll(rolesToAssign);

        for (Role ur : rolesToUnassign) {
            unassignRole(user, ur);
        }

        for (Role r : rolesToAssign) {
            assignRole(user, r);
        }
    }
}
