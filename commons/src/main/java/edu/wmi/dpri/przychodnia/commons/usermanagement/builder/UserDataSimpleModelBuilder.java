package edu.wmi.dpri.przychodnia.commons.usermanagement.builder;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.UserDataSimpleModel;

/**
 * Created by lupus on 14.11.16.
 */
public final class UserDataSimpleModelBuilder {
    private Long userId;
    private String name;
    private String pesel;
    private String email;
    private String telephone;
    private String address;
    private String mailingAddress;
    private Long entityId;
    private String entityType;

    private UserDataSimpleModelBuilder() {
    }

    public UserDataSimpleModelBuilder withEntityId(Long entityId) {
        this.entityId = entityId;
        return this;
    }

    public UserDataSimpleModelBuilder withEntityType(String entityType) {
        this.entityType = entityType;
        return this;
    }

    public static UserDataSimpleModelBuilder anUserDataSimpleModel() {
        return new UserDataSimpleModelBuilder();
    }

    public UserDataSimpleModelBuilder withUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public UserDataSimpleModelBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UserDataSimpleModelBuilder withPesel(String pesel) {
        this.pesel = pesel;
        return this;
    }

    public UserDataSimpleModelBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserDataSimpleModelBuilder withTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public UserDataSimpleModelBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    public UserDataSimpleModelBuilder withMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
        return this;
    }

    public UserDataSimpleModel build() {
        UserDataSimpleModel userDataSimpleModel = new UserDataSimpleModel();
        userDataSimpleModel.setUserId(userId);
        userDataSimpleModel.setName(name);
        userDataSimpleModel.setPesel(pesel);
        userDataSimpleModel.setEmail(email);
        userDataSimpleModel.setTelephone(telephone);
        userDataSimpleModel.setAddress(address);
        userDataSimpleModel.setMailingAddress(mailingAddress);
        userDataSimpleModel.setEntityId(entityId);
        userDataSimpleModel.setEntityType(entityType);
        return userDataSimpleModel;
    }
}
