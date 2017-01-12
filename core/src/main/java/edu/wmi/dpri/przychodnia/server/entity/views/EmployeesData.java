package edu.wmi.dpri.przychodnia.server.entity.views;

import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@ToString(callSuper = true)
@Entity
@Table(name = "v_employees_data")
@Immutable
public class EmployeesData extends BaseUserData {
    @Id
    @Column(name = "id")
    @Getter
    Long id;

    @Column(name = "doctor_id")
    @Getter
    Long doctorId;
    @Column(name = "employee_active")
    @Getter
    Boolean employeeActive;

    @Column(name = "doctor_active")
    @Getter
    Boolean doctorActive;

    public EmployeesData() {
        super();
    }
}
