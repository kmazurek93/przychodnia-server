package edu.wmi.dpri.przychodnia.server.security.webmodel;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by lupus on 22.10.16.
 */
public class UserContextWebModel {
    private String username;
    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    private List<String> rolesAssigned = newArrayList();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRolesAssigned() {
        return rolesAssigned;
    }

    public void setRolesAssigned(List<String> rolesAssigned) {
        this.rolesAssigned = rolesAssigned;
    }
}
