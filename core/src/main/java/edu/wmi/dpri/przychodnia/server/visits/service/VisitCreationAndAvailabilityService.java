package edu.wmi.dpri.przychodnia.server.visits.service;

import edu.wmi.dpri.przychodnia.commons.visits.enums.VisitStatusType;
import edu.wmi.dpri.przychodnia.commons.visits.webmodel.CalendarRequestModel;
import edu.wmi.dpri.przychodnia.commons.visits.webmodel.SimpleVisitWebModel;
import edu.wmi.dpri.przychodnia.server.entity.Doctor;
import edu.wmi.dpri.przychodnia.server.entity.Patient;
import edu.wmi.dpri.przychodnia.server.entity.Visit;
import edu.wmi.dpri.przychodnia.server.entity.procedures.DoctorCalendar;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.ErrorMessage;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.auth.ForbiddenException;
import edu.wmi.dpri.przychodnia.server.repository.PatientRepository;
import edu.wmi.dpri.przychodnia.server.repository.TimeWindowRepository;
import edu.wmi.dpri.przychodnia.server.repository.VisitRepository;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

import static edu.wmi.dpri.przychodnia.commons.visits.webmodel.AvailabilityStatus.FREE;
import static edu.wmi.dpri.przychodnia.server.exceptionmanagement.generators.ErrorMessageGenerator.getForbiddenErrorMessage;
import static org.hibernate.Hibernate.initialize;

/**
 * Created by lupus on 01.01.17.
 */
@Component
public class VisitCreationAndAvailabilityService {
    @Inject
    private VisitRepository visitRepository;
    @Inject
    private PatientRepository patientRepository;
    @Inject
    private DoctorService doctorService;
    @Inject
    private TimeWindowRepository timeWindowRepository;
    @Inject
    private DoctorCalendarService doctorCalendarService;

    @Transactional
    public Visit createNewVisit(SimpleVisitWebModel visitRequest) {
        Integer timeWindowOrder = checkIfVisitWindowIsAvailable(visitRequest);
        Visit visit = new Visit();
        Doctor doctor = doctorService.findById(visitRequest.getDoctorId());
        visit.setDoctor(doctor);
        Patient byPesel = patientRepository.findByPesel(visitRequest.getPatientPesel());
        if(byPesel == null) {
            ErrorMessage errorMessage = getForbiddenErrorMessage("NOT_A_PATIENT");
            throw new ForbiddenException(errorMessage);
        }
        visit.setPatient(byPesel);
        LocalTime time = new LocalTime(visitRequest.getStart());
        visit.setTimeWindow(timeWindowRepository.findByOrderAndStartTime(timeWindowOrder, time));
        visit.setStatus(VisitStatusType.NEW);
        LocalDate date = new LocalDate(visitRequest.getStart());
        visit.setDate(date);
        visit = visitRepository.save(visit);
        initialize(visit.getDoctor());
        initialize(visit.getTimeWindow());
        initialize(visit.getPatient());
        initialize(visit.getPatient().getPerson());
        initialize(visit.getAssociatedVisit());
        initialize(visit.getVisitsAssociatedWith());
        return visit;

    }


    public Integer checkIfVisitWindowIsAvailable(SimpleVisitWebModel visitRequest) {
        CalendarRequestModel calendarRequest = new CalendarRequestModel();
        calendarRequest.setDoctorId(visitRequest.getDoctorId());
        calendarRequest.setStartDate(visitRequest.getStart());
        calendarRequest.setEndDate(visitRequest.getStart()); //this is not an error
        LocalTime localTime = new LocalTime(visitRequest.getStart());
        List<DoctorCalendar> doctorCalendar = doctorCalendarService.getDoctorCalendar(calendarRequest);
        if (doctorCalendar.isEmpty() || isUnavailable(doctorCalendar)) {
            ErrorMessage errorMessage = getForbiddenErrorMessage("DOCTOR_NOT_AVAILABLE");
            throw new ForbiddenException(errorMessage);
        } else {
            List<DoctorCalendar> freeVisitsOn = doctorCalendar.stream()
                    .filter(o -> o.getState().equals(FREE))
                    .filter(o -> o.getStartTime().equals(localTime))
                    .collect(Collectors.toList());
            if (freeVisitsOn.isEmpty()) {
                ErrorMessage errorMessage = getForbiddenErrorMessage("DATE_NOT_AVAILABLE");
                throw new ForbiddenException(errorMessage);
            }
            return freeVisitsOn.get(0).getOrder();
        }
    }

    private boolean isUnavailable(List<DoctorCalendar> list) {
        return list.size() == 1 && !list.get(0).getState().equals(FREE);
    }
}
