package edu.wmi.dpri.przychodnia.server.repository;

import edu.wmi.dpri.przychodnia.server.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Query("select e from Employee e inner join fetch e.doctor d where d.id=:id")
    Employee findByDoctorId(@Param("id") Long doctorId);

    Employee findByPersonPESEL(String pesel);
}
