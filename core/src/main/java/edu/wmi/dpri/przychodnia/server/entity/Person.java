package edu.wmi.dpri.przychodnia.server.entity;

import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;


/**
 * Created by kmazu on 12.06.2016.
 */
@Entity
@Table(name = "persons")
public class Person {

    @Id
    @Column(name = "pesel", length = 11)
    private String PESEL;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "middle_name", length = 100)
    private String middleName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Column(name = "birthdate", nullable = false)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private DateTime birthDate;

    @Column(name = "birthplace", nullable = false, length = 100)
    private String birthPlace;

    @Column(name = "id_number", nullable = false, length = 40)
    private String idNumber;


    @ManyToOne(targetEntity = IdType.class)
    @JoinColumn(name = "id_type")
    private IdType idType;


    @ManyToOne(targetEntity = Address.class)
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne(targetEntity = Address.class)
    @JoinColumn(name = "mailing_address_id")
    private Address mailingAddress;


    @ManyToOne(targetEntity = Sex.class)
    @JoinColumn(name = "sex_id")
    private Sex sex;

    @Column(name = "telephone", length = 20)
    private String telephone;

    @OneToMany(targetEntity = User.class, mappedBy = "person")
    private List<User> users = newArrayList();

    @OneToOne(targetEntity = Employee.class, mappedBy = "person")
    private Employee employee;

    @OneToOne(targetEntity = Patient.class, mappedBy = "person")
    private Patient patient;

    public String getPESEL() {
        return PESEL;
    }

    public void setPESEL(String PESEL) {
        this.PESEL = PESEL;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public DateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(DateTime birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public IdType getIdType() {
        return idType;
    }

    public void setIdType(IdType idType) {
        this.idType = idType;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(Address mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Transient
    public User getUserIfExists() {
        return this.users != null && !this.users.isEmpty() ? this.users.get(0) : null;
    }

    public Person() {
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
