package edu.wmi.dpri.przychodnia.server.repository;

import edu.wmi.dpri.przychodnia.server.entity.TimeWindow;
import org.joda.time.LocalTime;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lupus on 11.12.16.
 */
@Repository
public interface TimeWindowRepository extends CrudRepository<TimeWindow, Long> {

    TimeWindow findByOrderAndStartTime(Integer order, LocalTime time);
}
