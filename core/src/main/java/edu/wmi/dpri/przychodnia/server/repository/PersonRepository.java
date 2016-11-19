package edu.wmi.dpri.przychodnia.server.repository;

import edu.wmi.dpri.przychodnia.server.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kmazu on 03.07.2016.
 */
@Repository
public interface PersonRepository extends CrudRepository<Person, String> {

    List<Person> findByFirstNameLikeOrMiddleNameLikeOrLastNameLikeOrTelephoneLike(
            String fName, String mName, String lName, String telephone
    );
}
