package edu.wmi.dpri.przychodnia.server.usermanagement.service;

import edu.wmi.dpri.przychodnia.server.entity.Person;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.ExceptionCause;
import edu.wmi.dpri.przychodnia.server.repository.PersonRepository;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static edu.wmi.dpri.przychodnia.server.exceptionmanagement.NotFoundExceptionThrower.throwExceptionIfNull;

/**
 * Created by lupus on 18.10.16.
 */
@Service
public class PersonService {

    @Inject
    private PersonRepository personRepository;

    @Transactional
    public Person createPerson(Person person) {
        return personRepository.save(person);
    }
    @Transactional(readOnly = true)
    public Person findOne(String pesel) {
        return personRepository.findOne(pesel);
    }
    @Transactional(readOnly = true)
    public Boolean personExists(String pesel) {
        Person one = findOne(pesel);
        return (one != null);
    }
    @Transactional
    public Person findOneAndInitialize(String pesel) {
        Person person = personRepository.findOne(pesel);
        throwExceptionIfNull(pesel, person, ExceptionCause.RETRIEVAL, Person.class);
        Hibernate.initialize(person.getAddress());
        Hibernate.initialize(person.getMailingAddress());
        Hibernate.initialize(person.getIdType());
        Hibernate.initialize(person.getUsers());
        Hibernate.initialize(person.getSex());
        return person;
    }


}
