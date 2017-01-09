package edu.wmi.dpri.przychodnia.server.usermanagement.service;

import edu.wmi.dpri.przychodnia.server.entity.*;
import edu.wmi.dpri.przychodnia.server.repository.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;
import static edu.wmi.dpri.przychodnia.server.security.model.RoleAuthority.ROLE_STAFF;

/**
 * Created by lupus on 08.01.17.
 */
@Component
public class RoleAndDbRelationService {
    @Inject
    private DoctorRepository doctorRepository;
    @Inject
    private EmployeeRepository employeeRepository;
    @Inject
    private PatientRepository patientRepository;
    @Inject
    private UserRepository userRepository;
    @Inject
    private PersonRepository personRepository;


    @Transactional
    public void addAsEmployee(User user) {
        Person person = user.getPerson();
        Employee employee = person.getEmployee();
        if (employee == null) {
            employee = new Employee();
            employee.setPerson(person);
            employee.setActive(true);
            person.setEmployee(employee);
            personRepository.save(person);
            employeeRepository.save(employee);
        } else {
            employee.setActive(true);
            employeeRepository.save(employee);
        }
    }

    @Transactional
    public void addAsPatient(User user) {
        Person person = user.getPerson();
        Patient patient = person.getPatient();
        if (patient == null) {
            patient = new Patient();
            patient.setActive(true);
            patient.setPerson(person);
        } else {
            patient.setActive(true);
        }
        patientRepository.save(patient);
    }

    @Transactional
    public void addAsDoctor(User user) {
        Person person = user.getPerson();
        Employee employee = person.getEmployee();
        if (employee == null) {
            addAsEmployee(user);
            employee = person.getEmployee();
        } else {
            employee.setActive(true);
            employeeRepository.save(employee);
        }
        Doctor doctor = employee.getDoctor();
        if (doctor == null) {
            doctor = new Doctor();
            doctor.setEmployee(employee);
            doctor.setActive(true);
            doctor.setSpecialisations(newArrayList());
        } else {
            doctor.setActive(true);
        }
        doctorRepository.save(doctor);
    }

    @Transactional
    public void removeStaffPrivileges(User user) {
        Person person = user.getPerson();
        Employee employee = person.getEmployee();
        if (employee != null) {
            employee.setActive(false);
            Doctor doctor = employee.getDoctor();
            if (doctor != null) {
                doctor.setActive(false);
                doctorRepository.save(doctor);
            }
            employeeRepository.save(employee);
        }
    }

    @Transactional
    public void removeDoctorPrivileges(User user) {
        Person person = user.getPerson();
        Employee employee = person.getEmployee();
        if (employee != null) {
            Doctor doctor = employee.getDoctor();
            if (doctor != null) {
                doctor.setActive(false);
                doctorRepository.save(doctor);
            }
            employeeRepository.save(employee);
        }
    }

    @Transactional
    public void removePatientPrivileges(User user) {
        Person person = user.getPerson();
        Patient patient = person.getPatient();
        if (patient != null) {
            patient.setActive(false);
            patientRepository.save(patient);
        }
    }

    @Transactional
    public void removeAdminPrivileges(User user) {
        List<Role> roles = user.getRoles();
        List<Role> staffRoles = roles.stream()
                .filter(o -> o.getName().equals(ROLE_STAFF))
                .collect(Collectors.toList());
        if (staffRoles.isEmpty()) {
            removeStaffPrivileges(user);
        }

    }
}
