package edu.wmi.dpri.przychodnia.server.repository;

import edu.wmi.dpri.przychodnia.server.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kmazu on 03.07.2016.
 */
@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

    Role findByName(String name);

    List<Role> findByNameIn(List<String> names);
}
