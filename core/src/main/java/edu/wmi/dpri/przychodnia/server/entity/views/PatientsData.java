package edu.wmi.dpri.przychodnia.server.entity.views;

import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by khartv on 30.11.2016.
 */
@Entity
@Table(name = "v_patients_data")
@Immutable
public class PatientsData extends BaseUserData{
    @Id
    @Column(name = "id")
    Long id;

    public PatientsData() {
        super();
    }

    public Long getId() {
        return id;
    }
}
