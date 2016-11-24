package edu.wmi.dpri.przychodnia.server.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by khartv on 21.11.2016.
 */
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
    private List<Hire> hiresWithPosition;

    public Position() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Hire> getHiresWithPosition() {
        return hiresWithPosition;
    }

    public void setHiresWithPosition(List<Hire> hiresWithPosition) {
        this.hiresWithPosition = hiresWithPosition;
    }


}
