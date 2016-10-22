package edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kmazu on 06.07.2016.
 */
public class CompleteUserDataSimpleModel {
    private Long userId;
    private String PESEL;
    private String firstName;
    private String middleName;
    private String lastName;
    private String emailAddress;
    private List<String> userRoles = new ArrayList<>();
    private List<String> parentAccountNames = new ArrayList<>();
    private List<String> childAccountNames = new ArrayList<>();


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPESEL() {
        return PESEL;
    }

    public void setPESEL(String PESEL) {
        this.PESEL = PESEL;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public List<String> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<String> userRoles) {
        this.userRoles = userRoles;
    }

    public List<String> getParentAccountNames() {
        return parentAccountNames;
    }

    public void setParentAccountNames(List<String> parentAccountNames) {
        this.parentAccountNames = parentAccountNames;
    }

    public List<String> getChildAccountNames() {
        return childAccountNames;
    }

    public void setChildAccountNames(List<String> childAccountNames) {
        this.childAccountNames = childAccountNames;
    }
}
