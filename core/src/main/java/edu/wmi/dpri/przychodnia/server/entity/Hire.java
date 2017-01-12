package edu.wmi.dpri.przychodnia.server.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;



@Data
@NoArgsConstructor
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


}
