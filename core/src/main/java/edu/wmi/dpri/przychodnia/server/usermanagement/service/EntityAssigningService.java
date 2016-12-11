package edu.wmi.dpri.przychodnia.server.usermanagement.service;

import edu.wmi.dpri.przychodnia.server.entity.Patient;
import edu.wmi.dpri.przychodnia.server.entity.Person;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.ExceptionCause;
import edu.wmi.dpri.przychodnia.server.repository.DoctorRepository;
import edu.wmi.dpri.przychodnia.server.repository.EmployeeRepository;
import edu.wmi.dpri.przychodnia.server.repository.PatientRepository;
import edu.wmi.dpri.przychodnia.server.repository.PersonRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static edu.wmi.dpri.przychodnia.server.exceptionmanagement.NotFoundExceptionThrower.throwExceptionIfNull;

/**
 * Created by lupus on 11.12.16.
 */
@Component
public class EntityAssigningService {
    
    @Inject
    private EmployeeRepository employeeRepository;
    @Inject
    private PatientRepository patientRepository;
    @Inject
    private DoctorRepository doctorRepository;
    @Inject
    private PersonRepository personRepository;
    
    @Transactional
    public void assignPersonToPatients(String pesel) {
        Person person = personRepository.findOne(pesel);
        throwExceptionIfNull(pesel, person, ExceptionCause.RETRIEVAL, Person.class);
        Patient patient = patientRepository.findByPesel(pesel);
        if(patient == null) {
            patient = new Patient();
            patient.setPerson(person);
            patientRepository.save(patient);
        }
    }
    
    @Transactional
    public void assignPersonToEmployees(String pesel) {
        
    }
    
    @Transactional
    public void assignPersonToDoctors(String pesel) {
        
    }
}
