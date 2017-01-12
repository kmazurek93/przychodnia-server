package edu.wmi.dpri.przychodnia.server.usermanagement.service;

import edu.wmi.dpri.przychodnia.server.entity.Patient;
import edu.wmi.dpri.przychodnia.server.repository.PatientRepository;
import edu.wmi.dpri.przychodnia.server.usermanagement.state.UserRegisteringState;
import org.springframework.stereotype.Component;

import javax.inject.Inject;


@Component
public class PatientService {
    @Inject
    private PatientRepository patientRepository;

    public void addPatient(UserRegisteringState state) {
        Patient patient = new Patient();
        patient.setPerson(state.getSavedPerson());
        patient.setActive(true);
        patientRepository.save(patient);
    }
}
