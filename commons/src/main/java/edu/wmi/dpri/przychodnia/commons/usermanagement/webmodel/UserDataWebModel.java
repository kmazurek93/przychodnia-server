package edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel;

import javax.validation.constraints.NotNull;
import java.util.List;

import static edu.wmi.dpri.przychodnia.commons.usermanagement.UserManagementConstants.DEFAULT_ROLES_FOR_NEW_USER;

/**
 * Created by lupus on 18.10.16.
 */
public class UserDataWebModel {
    private Long id;

    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String emailAddress;
    private List<String> roles = DEFAULT_ROLES_FOR_NEW_USER;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
