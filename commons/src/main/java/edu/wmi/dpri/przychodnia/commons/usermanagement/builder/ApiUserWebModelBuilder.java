package edu.wmi.dpri.przychodnia.commons.usermanagement.builder;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.AddressWebModel;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.ApiUserWebModel;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.RoleWebModel;

import java.util.List;

/**
 * Created by kmazu on 06.07.2016.
 */
public final class ApiUserWebModelBuilder {
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

    private ApiUserWebModelBuilder() {
    }

    public static ApiUserWebModelBuilder anApiUserWebModel() {
        return new ApiUserWebModelBuilder();
    }

    public ApiUserWebModelBuilder withUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public ApiUserWebModelBuilder withPESEL(String PESEL) {
        this.PESEL = PESEL;
        return this;
    }

    public ApiUserWebModelBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ApiUserWebModelBuilder withMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public ApiUserWebModelBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ApiUserWebModelBuilder withAddress(AddressWebModel address) {
        this.address = address;
        return this;
    }

    public ApiUserWebModelBuilder withUserRoles(List<RoleWebModel> userRoles) {
        this.userRoles = userRoles;
        return this;
    }

    public ApiUserWebModelBuilder withParentAccountIds(List<Integer> parentAccountIds) {
        this.parentAccountIds = parentAccountIds;
        return this;
    }

    public ApiUserWebModelBuilder withChildAccountIds(List<Integer> childAccountIds) {
        this.childAccountIds = childAccountIds;
        return this;
    }

    public ApiUserWebModelBuilder withEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public ApiUserWebModelBuilder withSex(String sex) {
        this.sex = sex;
        return this;
    }

    public ApiUserWebModelBuilder withIdNumber(String idNumber) {
        this.idNumber = idNumber;
        return this;
    }

    public ApiUserWebModelBuilder withIdType(String idType) {
        this.idType = idType;
        return this;
    }

    public ApiUserWebModelBuilder withLogin(String login) {
        this.login = login;
        return this;
    }

    public ApiUserWebModelBuilder withPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
        return this;
    }

    public ApiUserWebModel build() {
        ApiUserWebModel apiUserWebModel = new ApiUserWebModel();
        apiUserWebModel.setUserId(userId);
        apiUserWebModel.setPESEL(PESEL);
        apiUserWebModel.setFirstName(firstName);
        apiUserWebModel.setMiddleName(middleName);
        apiUserWebModel.setLastName(lastName);
        apiUserWebModel.setAddress(address);
        apiUserWebModel.setUserRoles(userRoles);
        apiUserWebModel.setParentAccountIds(parentAccountIds);
        apiUserWebModel.setChildAccountIds(childAccountIds);
        apiUserWebModel.setEmailAddress(emailAddress);
        apiUserWebModel.setSex(sex);
        apiUserWebModel.setIdNumber(idNumber);
        apiUserWebModel.setIdType(idType);
        apiUserWebModel.setLogin(login);
        apiUserWebModel.setPasswordHash(passwordHash);
        return apiUserWebModel;
    }
}
