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
@Table(name = "id_types")
public class IdType {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;

    @OneToMany(targetEntity = Person.class, mappedBy = "idType")
    private List<Person> persons = newArrayList();

}
