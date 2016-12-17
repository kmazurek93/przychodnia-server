package edu.wmi.dpri.przychodnia.server.entity.views;

import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by khartv on 30.11.2016.
 */

@ToString(callSuper = true)
@Entity
@Table(name = "v_employees_data")
@Immutable
public class EmployeesData extends BaseUserData{
    @Id
    @Column(name = "id")
    @Getter Long id;

    @Column(name = "doctor_id")
    @Getter Long doctorId;

    public EmployeesData() {
        super();
    }
}
