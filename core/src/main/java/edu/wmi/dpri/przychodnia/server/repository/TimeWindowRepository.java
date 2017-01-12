package edu.wmi.dpri.przychodnia.server.repository;

import edu.wmi.dpri.przychodnia.server.entity.TimeWindow;
import org.joda.time.LocalTime;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TimeWindowRepository extends CrudRepository<TimeWindow, Long> {
    List<TimeWindow> findAllByOrderByIdAsc();

    TimeWindow findByOrderAndStartTime(Integer order, LocalTime time);
}
