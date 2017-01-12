package edu.wmi.dpri.przychodnia.server.security.model;

import edu.wmi.dpri.przychodnia.server.entity.*;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;



public class UserContext {
    @Getter
    private final String username;
    @Getter
    private final Long userId;
    @Getter
    private final Long doctorId;
    @Getter
    private final Long patientId;
    @Getter
    private final Long employeeId;
    @Getter
    private final String pesel;
    @Getter
    private final List<GrantedAuthority> authorities;

    private UserContext(User user, List<GrantedAuthority> authorities) {
        this.username = user.getLogin();
        this.authorities = authorities;
        this.userId = user.getId();
        Person person = user.getPerson();
        Employee employee = person.getEmployee();
        Patient patient = person.getPatient();
        this.pesel = person.getPESEL();
        if (patient != null) {
            this.patientId = patient.getId();
        } else {
            this.patientId = null;
        }
        if (employee != null) {
            this.employeeId = employee.getId();
            Doctor doctor = employee.getDoctor();
            if (doctor != null) {
                this.doctorId = doctor.getId();
            } else {
                this.doctorId = null;
            }
        } else {
            this.employeeId = null;
            this.doctorId = null;
        }
    }

    public static UserContext create(User user, List<GrantedAuthority> authorities) {
        if (StringUtils.isBlank(user.getLogin()) || user.getId() == null)
            throw new IllegalArgumentException("Username is blank");
        return new UserContext(user, authorities);
    }

}
