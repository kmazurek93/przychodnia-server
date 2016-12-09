package edu.wmi.dpri.przychodnia.server.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by kmazu on 12.06.2016.
 */

@Data
@NoArgsConstructor
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
}
