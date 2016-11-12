package edu.wmi.dpri.przychodnia.server.entity.builder;

import edu.wmi.dpri.przychodnia.server.entity.*;
import org.joda.time.DateTime;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by lupus on 19.09.16.
 */
public final class PersonBuilder {
    private String PESEL;
    private String firstName;
    private String middleName;
    private String lastName;
    private DateTime birthDate;
    private String birthPlace;
    private String idNumber;
    private IdType idType;
    private Address address;
    private Address mailingAddress;
    private Sex sex;
    private String telephone;
    private List<User> users = newArrayList();

    private PersonBuilder() {
    }

    public static PersonBuilder aPerson() {
        return new PersonBuilder();
    }

    public PersonBuilder withTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public PersonBuilder withPESEL(String PESEL) {
        this.PESEL = PESEL;
        return this;
    }

    public PersonBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public PersonBuilder withMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public PersonBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public PersonBuilder withBirthDate(DateTime birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public PersonBuilder withBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
        return this;
    }

    public PersonBuilder withIdNumber(String idNumber) {
        this.idNumber = idNumber;
        return this;
    }

    public PersonBuilder withIdType(IdType idType) {
        this.idType = idType;
        return this;
    }

    public PersonBuilder withAddress(Address address) {
        this.address = address;
        return this;
    }

    public PersonBuilder withMailingAddress(Address mailingAddress) {
        this.mailingAddress = mailingAddress;
        return this;
    }

    public PersonBuilder withSex(Sex sex) {
        this.sex = sex;
        return this;
    }

    public PersonBuilder withUsers(List<User> users) {
        this.users = users;
        return this;
    }

    public Person build() {
        Person person = new Person();
        person.setPESEL(PESEL);
        person.setFirstName(firstName);
        person.setMiddleName(middleName);
        person.setLastName(lastName);
        person.setBirthDate(birthDate);
        person.setBirthPlace(birthPlace);
        person.setIdNumber(idNumber);
        person.setIdType(idType);
        person.setAddress(address);
        person.setMailingAddress(mailingAddress);
        person.setSex(sex);
        person.setUsers(users);
        return person;
    }
}
