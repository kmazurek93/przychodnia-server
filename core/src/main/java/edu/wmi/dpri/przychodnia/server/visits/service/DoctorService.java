package edu.wmi.dpri.przychodnia.server.visits.service;

import edu.wmi.dpri.przychodnia.server.entity.Doctor;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.ExceptionCause;
import edu.wmi.dpri.przychodnia.server.repository.DoctorRepository;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

import static edu.wmi.dpri.przychodnia.server.exceptionmanagement.NotFoundExceptionThrower.throwExceptionIfNull;

/**
 * Created by lupus on 01.01.17.
 */
@Component
public class DoctorService {
    @Inject
    private DoctorRepository doctorRepository;

    public Doctor findById(Long id) {
        Doctor doctor = doctorRepository.findOne(id);
        throwExceptionIfNull(id, doctor, ExceptionCause.RETRIEVAL, Doctor.class);
        return doctor;
    }
}
