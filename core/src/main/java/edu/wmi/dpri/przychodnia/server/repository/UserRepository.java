package edu.wmi.dpri.przychodnia.server.repository;

import edu.wmi.dpri.przychodnia.server.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Created by kmazu on 03.07.2016.
 */

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByLogin(String login);

    User findByEmailAddress(String email);

    List<User> findByActiveTrue();

    List<User> findByIdIn(Set<Long> ids);

    @Query("select u from User u inner join fetch u.person where u.emailAddress like :likeEmail ")
    List<User> findByEmailAddressLike(@Param("likeEmail") String likeEmail);



}
