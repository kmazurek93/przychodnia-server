package edu.wmi.dpri.przychodnia.server.repository;

import edu.wmi.dpri.przychodnia.server.entity.Patient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface PatientRepository extends CrudRepository<Patient, Long> {

    @Query("select p from Patient p inner join fetch p.person pe where pe.PESEL = :pesel")
    Patient findByPesel(@Param("pesel") String pesel);

}
