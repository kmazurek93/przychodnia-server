package edu.wmi.dpri.przychodnia.server.repository;

import edu.wmi.dpri.przychodnia.server.entity.IdType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by kmazu on 03.07.2016.
 */
@Repository
public interface IdTypeRepository extends CrudRepository<IdType, Integer> {

}
