package edu.wmi.dpri.przychodnia.server.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;




@Data
@NoArgsConstructor
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

    @Column(name = "telephone", length = 20, nullable = false)
    private String telephone;

    @OneToMany(targetEntity = User.class, mappedBy = "person")
    private List<User> users = newArrayList();

    @OneToOne(targetEntity = Employee.class, mappedBy = "person")
    private Employee employee;

    @OneToOne(targetEntity = Patient.class, mappedBy = "person")
    private Patient patient;

    @Transient
    public User getUserIfExists() {
        return this.users != null && !this.users.isEmpty() ? this.users.get(0) : null;
    }

}
