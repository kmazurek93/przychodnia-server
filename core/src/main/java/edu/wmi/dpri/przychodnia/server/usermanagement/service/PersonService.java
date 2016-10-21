package edu.wmi.dpri.przychodnia.server.usermanagement.service;

import edu.wmi.dpri.przychodnia.server.entity.Person;
import edu.wmi.dpri.przychodnia.server.repository.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created by lupus on 18.10.16.
 */
@Service
public class PersonService {

    @Inject
    private PersonRepository personRepository;

    @Transactional
    public Person createPerson(Person person, String PESEL) {
        person.setPESEL(PESEL);
        return personRepository.save(person);
    }
}
