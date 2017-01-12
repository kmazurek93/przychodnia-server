package edu.wmi.dpri.przychodnia.server.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;



@Data
@NoArgsConstructor
@Entity
@Table(name = "positions")
public class Position {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;

    @OneToMany(targetEntity = Hire.class, mappedBy = "position")
    private List<Hire> hiresWithPosition = newArrayList();

}
