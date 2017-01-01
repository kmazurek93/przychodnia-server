package edu.wmi.dpri.przychodnia.server.security.webmodel;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by lupus on 22.10.16.
 */
@Data
@NoArgsConstructor
public class UserContextWebModel {
    private String username;
    private String firstName;
    private String lastName;
    private Long userId;
    private Long doctorId;
    private Long employeeId;
    private Long patientId;
    private String pesel;
    private List<String> rolesAssigned = newArrayList();

}
