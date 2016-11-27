package edu.wmi.dpri.przychodnia.server.repository;

import edu.wmi.dpri.przychodnia.server.entity.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kmazu on 03.07.2016.
 */
@Repository
public interface PersonRepository extends CrudRepository<Person, String> {

    @Query("Select p from Person p inner join fetch p.users where (p.firstName like :name " +
            "or p.middleName like :name or p.lastName like :name) or p.telephone like :telephone")
    List<Person> searchQueryOnNamesAndPhone(@Param("name") String likeName, @Param("telephone") String likeTelephone);

}
