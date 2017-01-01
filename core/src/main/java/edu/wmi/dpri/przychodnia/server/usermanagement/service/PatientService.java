package edu.wmi.dpri.przychodnia.server.usermanagement.service;

import edu.wmi.dpri.przychodnia.server.entity.Patient;
import edu.wmi.dpri.przychodnia.server.repository.PatientRepository;
import edu.wmi.dpri.przychodnia.server.usermanagement.state.UserRegisteringState;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * Created by lupus on 01.01.17.
 */
@Component
public class PatientService {
    @Inject
    private PatientRepository patientRepository;

    public void addPatient(UserRegisteringState state) {
        Patient patient = new Patient();
        patient.setPerson(state.getSavedPerson());
        patientRepository.save(patient);
    }
}
