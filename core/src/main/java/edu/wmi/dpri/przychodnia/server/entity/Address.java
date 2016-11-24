package edu.wmi.dpri.przychodnia.server.entity;

import javax.persistence.*;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by kmazu on 12.06.2016.
 */
@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "country", nullable = false, length = 50)
    private String country;

    @Column(name = "province", length = 50)
    private String province;

    @Column(name = "city", nullable = false, length = 75)
    private String city;

    @Column(name = "street", nullable = false, length = 100)
    private String street;

    @Column(name = "house", nullable = false, length = 10)
    private String house;

    @Column(name = "apartment", length = 10)
    private String apartment;

    @Column(name = "postcode", length = 15)
    private String postCode;

    @OneToMany(targetEntity = Person.class, mappedBy = "address", cascade = CascadeType.PERSIST)
    private List<Person> persons = newArrayList();

    @OneToMany(targetEntity = Person.class, mappedBy = "mailingAddress", cascade = CascadeType.PERSIST)
    private List<Person> personsWithMailingAddress = newArrayList();

    public Address() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public List<Person> getPersonsWithMailingAddress() {
        return personsWithMailingAddress;
    }

    public void setPersonsWithMailingAddress(List<Person> personsWithMailingAddress) {
        this.personsWithMailingAddress = personsWithMailingAddress;
    }

}
