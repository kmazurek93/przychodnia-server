package edu.wmi.dpri.przychodnia.server.availability.service;

import edu.wmi.dpri.przychodnia.commons.availability.webmodel.DoctorAvailabilityWebModel;
import edu.wmi.dpri.przychodnia.server.entity.Availability;
import edu.wmi.dpri.przychodnia.server.entity.Doctor;
import edu.wmi.dpri.przychodnia.server.entity.TimeWindow;
import edu.wmi.dpri.przychodnia.server.entity.enums.WeekdayType;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.ExceptionCause;
import edu.wmi.dpri.przychodnia.server.repository.AvailabilityRepository;
import edu.wmi.dpri.przychodnia.server.repository.DoctorRepository;
import edu.wmi.dpri.przychodnia.server.repository.TimeWindowRepository;
import org.hibernate.Hibernate;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;
import static edu.wmi.dpri.przychodnia.server.exceptionmanagement.NotFoundExceptionThrower.throwExceptionIfNull;

/**
 * Created by lupus on 10.01.17.
 */
@Component
public class AvailabilityService {

    @Inject
    private TimeWindowRepository timeWindowRepository;

    @Inject
    private DoctorRepository doctorRepository;
    @Inject
    private AvailabilityRepository availabilityRepository;

    @Transactional(readOnly = true)
    public List<TimeWindow> getTimeWindows() {
        return newArrayList(timeWindowRepository.findAllByOrderByIdAsc());
    }

    @Transactional(readOnly = true)
    public List<Availability> getDoctorAvailabilities(Long doctorId) {
        Doctor one = doctorRepository.findOne(doctorId);
        throwExceptionIfNull(doctorId, one, ExceptionCause.RETRIEVAL, Doctor.class);
        Hibernate.initialize(one.getAvailabilities());
        List<Availability> availabilities = one.getAvailabilities();
        availabilities.forEach(this::initializeChildEntities);
        return availabilities;
    }

    private void initializeChildEntities(Availability availability) {
        Hibernate.initialize(availability.getStartTimeWindow());
        Hibernate.initialize(availability.getEndTimeWindow());
    }

    @Transactional
    public Availability createDoctorAvailability(DoctorAvailabilityWebModel model) {
        Long doctorId = model.getDoctorId();
        Doctor doctor = doctorRepository.findOne(doctorId);
        throwExceptionIfNull(doctorId, doctor, ExceptionCause.CREATION, Doctor.class);
        TimeWindow start = timeWindowRepository.findOne(model.getTimeWindowStartId());
        throwExceptionIfNull(model.getTimeWindowStartId(), start, ExceptionCause.CREATION, TimeWindow.class);
        TimeWindow end = timeWindowRepository.findOne(model.getTimeWindowEndId());
        throwExceptionIfNull(model.getTimeWindowEndId(), end, ExceptionCause.CREATION, TimeWindow.class);
        Availability availability = new Availability();
        WeekdayType weekdayType = WeekdayType.valueOf(model.getWeekday());
        availability.setWeekday(weekdayType.getValue());
        availability.getDoctors().add(doctor);
        availability.setStartTimeWindow(start);
        availability.setEndTimeWindow(end);
        availability.setDateStart(new DateTime(model.getValidFrom()));
        availability.setDateEnd(new DateTime(model.getValidTo()));
        Availability saved = availabilityRepository.save(availability);
        initializeChildEntities(saved);
        return saved;
    }

    @Transactional
    public Availability updateDoctorAvailability(DoctorAvailabilityWebModel model) {
        Long doctorId = model.getDoctorId();
        Doctor doctor = doctorRepository.findOne(doctorId);
        throwExceptionIfNull(doctorId, doctor, ExceptionCause.CREATION, Doctor.class);
        List<Availability> availabilities = doctor.getAvailabilities();
        List<Availability> filtered = availabilities.stream()
                .filter(o -> o.getId().equals(model.getAvailabilityId()))
                .collect(Collectors.toList());
        if (filtered.isEmpty()) {
            throwExceptionIfNull(model.getAvailabilityId(), null, ExceptionCause.MODIFICATION, Availability.class);
        }
        Availability availability = filtered.get(0);
        TimeWindow start = timeWindowRepository.findOne(model.getTimeWindowStartId());
        throwExceptionIfNull(model.getTimeWindowStartId(), start, ExceptionCause.CREATION, TimeWindow.class);
        TimeWindow end = timeWindowRepository.findOne(model.getTimeWindowEndId());
        throwExceptionIfNull(model.getTimeWindowEndId(), end, ExceptionCause.CREATION, TimeWindow.class);
        availability.getDoctors().add(doctor);
        availability.setStartTimeWindow(start);
        availability.setEndTimeWindow(end);
        availability.setDateStart(new DateTime(model.getValidFrom()));
        availability.setDateEnd(new DateTime(model.getValidTo()));
        WeekdayType weekdayType = WeekdayType.valueOf(model.getWeekday());
        availability.setWeekday(weekdayType.getValue());
        Availability saved = availabilityRepository.save(availability);
        initializeChildEntities(saved);
        return saved;
    }

    @Transactional
    public void deleteDoctorAvailability(Long availabilityId, Long doctorId) {
        Doctor doctor = doctorRepository.findOne(doctorId);
        throwExceptionIfNull(doctorId, doctor, ExceptionCause.CREATION, Doctor.class);
        List<Availability> availabilities = doctor.getAvailabilities();
        List<Availability> filtered = availabilities.stream()
                .filter(o -> o.getId().equals(availabilityId))
                .collect(Collectors.toList());
        if (filtered.isEmpty()) {
            throwExceptionIfNull(availabilityId, null, ExceptionCause.MODIFICATION, Availability.class);
        }
        doctor.getAvailabilities().remove(filtered.get(0));
        doctorRepository.save(doctor);
        availabilityRepository.delete(availabilityId);
    }
}
