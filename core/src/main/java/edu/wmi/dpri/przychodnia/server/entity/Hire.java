package edu.wmi.dpri.przychodnia.server.entity;

import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by khartv on 21.11.2016.
 */
@Entity
@Table(name = "hires")
public class Hire {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Employee.class)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "date_start", nullable = false)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private DateTime dateStart;

    @Column(name = "date_end")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private DateTime dateEnd;

    @ManyToOne(targetEntity = Position.class)
    @JoinColumn(name = "position_id")
    private Position position;

    @Column(name = "salary", precision = 13, scale = 2)
    private BigDecimal salary;

    @ManyToOne(targetEntity = Employee.class)
    @JoinColumn(name = "supervisor")
    private Employee supervisor;

    public Hire() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public DateTime getDateStart() {
        return dateStart;
    }

    public void setDateStart(DateTime dateStart) {
        this.dateStart = dateStart;
    }

    public DateTime getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(DateTime dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Employee getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Employee supervisor) {
        this.supervisor = supervisor;
    }


}
