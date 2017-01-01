package edu.wmi.dpri.przychodnia.server.repository;

import edu.wmi.dpri.przychodnia.commons.visits.enums.VisitStatusType;
import edu.wmi.dpri.przychodnia.server.entity.Visit;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lupus on 11.12.16.
 */
@Repository
public interface VisitRepository extends CrudRepository<Visit, Long> {

    Long countByDoctorIdAndDateBetween(Long doctorId, LocalDate from, LocalDate to);

    Page<Visit> findByPatientPersonPESEL(String pesel, Pageable pageable);

    List<Visit> findByDoctorIdAndDateBetween(Long doctorId, LocalDate from, LocalDate to);

    List<Visit> findByPatientIdAndDateAfterAndStatus(Long patientId, LocalDate from, VisitStatusType status, Sort sort);

    List<Visit> findByDoctorIdAndDateAfterAndStatus(Long patientId, LocalDate from, VisitStatusType status, Sort sort);

    List<Visit> findByDoctorIdAndDateAndTimeWindowStartTime(Long doctorId, LocalDate date, LocalTime startTime);
}
