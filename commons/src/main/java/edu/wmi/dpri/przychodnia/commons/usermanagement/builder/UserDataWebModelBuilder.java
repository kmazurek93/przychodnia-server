package edu.wmi.dpri.przychodnia.commons.usermanagement.builder;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.UserDataWebModel;

import java.util.List;

import static edu.wmi.dpri.przychodnia.commons.usermanagement.UserManagementConstants.DEFAULT_ROLES_FOR_NEW_USER;

/**
 * Created by lupus on 14.11.16.
 */
public final class UserDataWebModelBuilder {
    private Long id;
    private String username;
    private String password;
    private String emailAddress;
    private List<String> roles = DEFAULT_ROLES_FOR_NEW_USER;
    private boolean active;

    private UserDataWebModelBuilder() {
    }

    public static UserDataWebModelBuilder anUserDataWebModel() {
        return new UserDataWebModelBuilder();
    }

    public UserDataWebModelBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public UserDataWebModelBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public UserDataWebModelBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserDataWebModelBuilder withEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public UserDataWebModelBuilder withRoles(List<String> roles) {
        this.roles = roles;
        return this;
    }

    public UserDataWebModelBuilder withActive(boolean active) {
        this.active = active;
        return this;
    }

    public UserDataWebModel build() {
        UserDataWebModel userDataWebModel = new UserDataWebModel();
        userDataWebModel.setId(id);
        userDataWebModel.setUsername(username);
        userDataWebModel.setPassword(password);
        userDataWebModel.setEmailAddress(emailAddress);
        userDataWebModel.setRoles(roles);
        userDataWebModel.setActive(active);
        return userDataWebModel;
    }
}
