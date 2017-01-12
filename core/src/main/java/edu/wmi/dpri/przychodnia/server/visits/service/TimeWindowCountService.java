package edu.wmi.dpri.przychodnia.server.visits.service;

import edu.wmi.dpri.przychodnia.server.entity.Availability;
import edu.wmi.dpri.przychodnia.server.entity.Doctor;
import edu.wmi.dpri.przychodnia.server.repository.DoctorRepository;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class TimeWindowCountService {

    @Inject
    private DoctorRepository doctorRepository;

    @Transactional(readOnly = true)
    public Long getMaximumVisitCountForDoctorAndDay(Long doctorId, DateTime day) {
        Doctor doctor = doctorRepository.findOne(doctorId);
        List<Availability> availabilities = doctor.getAvailabilities();
        int weekday = day.getDayOfWeek();

        List<Availability> filteredAvailabilities =
                availabilities.stream()
                        .filter(o -> o.getWeekday().equals(weekday)
                                &&
                                new Interval(o.getDateStart(), o.getDateEnd().withTime(23, 59, 59, 999)).contains(day))
                        .collect(Collectors.toList());

        long count = 0L;
        for (Availability a : filteredAvailabilities) {
            Integer orderStart = a.getStartTimeWindow().getOrder();
            Integer orderEnd = a.getEndTimeWindow().getOrder();
            count += (orderEnd - orderStart) + 1;
        }
        return count;
    }

    @Transactional(readOnly = true)
    public Long getMaximumVisitCountForDoctorAndMonth(Long doctorId, DateTime start) {
        DateTime monthBeginning = start.dayOfMonth().withMinimumValue().withTime(0, 0, 0, 0);
        DateTime monthEnd = start.dayOfMonth().withMaximumValue().withTime(23, 59, 59, 999);
        Interval interval = new Interval(monthBeginning, monthEnd);
        Long standardDays = interval.toDuration().getStandardDays();
        long count = 0L;
        for(int i = 0; i<standardDays.intValue(); i++) {
            count += getMaximumVisitCountForDoctorAndDay(doctorId, monthBeginning.plusDays(i));
        }
        return count;
    }
}
