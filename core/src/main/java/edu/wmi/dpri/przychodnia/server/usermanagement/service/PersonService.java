package edu.wmi.dpri.przychodnia.server.usermanagement.service;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.PersonalDataWebModel;
import edu.wmi.dpri.przychodnia.server.entity.IdType;
import edu.wmi.dpri.przychodnia.server.entity.Person;
import edu.wmi.dpri.przychodnia.server.entity.Sex;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.ExceptionCause;
import edu.wmi.dpri.przychodnia.server.repository.PersonRepository;
import org.hibernate.Hibernate;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

import static edu.wmi.dpri.przychodnia.server.exceptionmanagement.NotFoundExceptionThrower.throwExceptionIfNull;

/**
 * Created by lupus on 18.10.16.
 */
@Service
public class PersonService {

	@Inject
	private PersonRepository personRepository;
	@Inject
	private IdTypeService idTypeService;
	@Inject
	private SexService sexService;

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

	@Transactional(readOnly = true)
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

	@Transactional
	public void updatePerson(PersonalDataWebModel personalData, boolean isAdminOrStaff) {
		Person person = personRepository.findOne(personalData.getPesel());
		if (isAdminOrStaff) {
			person.setBirthDate(new DateTime(personalData.getBirthDate()));
			person.setBirthPlace(personalData.getBirthPlace());
			person.setFirstName(personalData.getFirstName());
			person.setLastName(personalData.getLastName());
			person.setMiddleName(personalData.getMiddleName());
			Sex sex = sexService.findOne(personalData.getSexId());
			person.setSex(sex);
			IdType idType = idTypeService.getById(personalData.getIdTypeNo());
			person.setIdType(idType);
			person.setIdNumber(personalData.getIdNumber());
		}
		person.setTelephone(personalData.getTelephone());
		personRepository.save(person);
	}

	@Transactional(readOnly = true)
	public List<Person> searchQueryOnNamesAndPhoneAndPesel(String likeName, String likeTelephone, String pesel) {
		return personRepository.searchQueryOnNamesAndPhoneAndPesel(likeName, likeTelephone, pesel);
	}
}
