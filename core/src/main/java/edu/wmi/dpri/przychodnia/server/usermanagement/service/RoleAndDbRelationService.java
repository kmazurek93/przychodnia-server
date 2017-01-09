package edu.wmi.dpri.przychodnia.server.usermanagement.service;

import edu.wmi.dpri.przychodnia.server.entity.*;
import edu.wmi.dpri.przychodnia.server.repository.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static com.google.common.collect.Lists.newArrayList;

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
            employee = employeeRepository.save(employee);
            person.setEmployee(employee);
            personRepository.savePerson(person);
        }
    }

    @Transactional
    public void addAsPatient(User user) {
        Person person = user.getPerson();
        Patient patient = person.getPatient();
        if (patient == null) {
            patient = new Patient();
            patient.setPerson(person);
            patientRepository.save(patient);
        }
    }

    @Transactional
    public void addAsDoctor(User user) {
        Person person = user.getPerson();
        Employee employee = person.getEmployee();
        if(employee == null) {
        	addAsEmployee(user);
        	employee = person.getEmployee();
        }
        Doctor doctor = new Doctor();
        doctor.setEmployee(employee);
        doctor.setSpecialisations(newArrayList());
        doctor = doctorRepository.save(doctor);
    }

}
