package edu.wmi.dpri.przychodnia.server.repository;

import edu.wmi.dpri.przychodnia.server.entity.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PersonRepository extends CrudRepository<Person, String> {

    @Query("Select p from Person p inner join fetch p.users where (p.firstName like :name " +
            "or p.middleName like :name or p.lastName like :name) and p.telephone like :telephone " +
            "and p.PESEL like :pesel")
    List<Person> searchQueryOnNamesAndPhoneAndPesel(@Param("name") String likeName,
                                                    @Param("telephone") String likeTelephone,
                                                    @Param("pesel") String pesel);

}
