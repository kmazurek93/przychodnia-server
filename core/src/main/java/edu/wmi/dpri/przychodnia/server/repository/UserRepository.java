package edu.wmi.dpri.przychodnia.server.repository;

import edu.wmi.dpri.przychodnia.server.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by kmazu on 03.07.2016.
 */

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByLogin(String login);
    User findByEmailAddress(String email);
}
