package edu.wmi.dpri.przychodnia.server.repository;

import edu.wmi.dpri.przychodnia.server.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by lupus on 11.12.16.
 */
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Query("select e from Employee e inner join fetch e.doctor d where d.id=:id")
    Employee findByDoctorId(@Param("id") Long doctorId);

    Employee findByPersonPESEL(String pesel);
}
