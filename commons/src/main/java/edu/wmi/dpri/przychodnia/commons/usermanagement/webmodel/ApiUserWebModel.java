package edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel;

import java.util.List;

/**
 * Created by kmazu on 06.07.2016.
 */
public class ApiUserWebModel {
	private Integer userId;
	private String PESEL;
	private String firstName;
    private String middleName;
	private String lastName;
	private AddressWebModel address;
	private List<RoleWebModel> userRoles;
	private List<Integer> parentAccountIds;
	private List<Integer> childAccountIds;
    private String emailAddress;
    private String sex;
    private String idNumber;
    private String idType;
    private String login;
    private String passwordHash;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
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

    public AddressWebModel getAddress() {
        return address;
    }

    public void setAddress(AddressWebModel address) {
        this.address = address;
    }

    public List<RoleWebModel> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<RoleWebModel> userRoles) {
        this.userRoles = userRoles;
    }

    public List<Integer> getParentAccountIds() {
        return parentAccountIds;
    }

    public void setParentAccountIds(List<Integer> parentAccountIds) {
        this.parentAccountIds = parentAccountIds;
    }

    public List<Integer> getChildAccountIds() {
        return childAccountIds;
    }

    public void setChildAccountIds(List<Integer> childAccountIds) {
        this.childAccountIds = childAccountIds;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
