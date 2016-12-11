package edu.wmi.dpri.przychodnia.server.repository;

import edu.wmi.dpri.przychodnia.server.entity.Specialisation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lupus on 11.12.16.
 */
@Repository
public interface SpecialisationRepository extends CrudRepository<Specialisation, Long> {
}
