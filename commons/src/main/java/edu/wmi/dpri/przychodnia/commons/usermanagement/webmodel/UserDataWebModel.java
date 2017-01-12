package edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

import static edu.wmi.dpri.przychodnia.commons.usermanagement.UserManagementConstants.DEFAULT_ROLES_FOR_NEW_USER;


@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class UserDataWebModel {
    private Long id;

    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String emailAddress;
    private List<String> roles = DEFAULT_ROLES_FOR_NEW_USER;
    private boolean active;

}
