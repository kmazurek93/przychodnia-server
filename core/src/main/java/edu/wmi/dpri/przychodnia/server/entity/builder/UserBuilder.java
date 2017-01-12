package edu.wmi.dpri.przychodnia.server.entity.builder;

import edu.wmi.dpri.przychodnia.server.entity.Person;
import edu.wmi.dpri.przychodnia.server.entity.Role;
import edu.wmi.dpri.przychodnia.server.entity.User;

import java.util.List;
import java.util.Set;


public final class UserBuilder {
    private Long id;
    private String login;
    private String password;
    private String emailAddress;
    private boolean active;
    private Person person;
    private Set<User> parentUsers;
    private Set<User> childUsers;
    private List<Role> roles;

    private UserBuilder() {
    }

    public static UserBuilder anUser() {
        return new UserBuilder();
    }

    public UserBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public UserBuilder withLogin(String login) {
        this.login = login;
        return this;
    }

    public UserBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder withEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public UserBuilder withActive(boolean active) {
        this.active = active;
        return this;
    }

    public UserBuilder withPerson(Person person) {
        this.person = person;
        return this;
    }

    public UserBuilder withParentUsers(Set<User> parentUsers) {
        this.parentUsers = parentUsers;
        return this;
    }

    public UserBuilder withChildUsers(Set<User> childUsers) {
        this.childUsers = childUsers;
        return this;
    }

    public UserBuilder withRoles(List<Role> roles) {
        this.roles = roles;
        return this;
    }

    public User build() {
        User user = new User();
        user.setId(id);
        user.setLogin(login);
        user.setPassword(password);
        user.setEmailAddress(emailAddress);
        user.setActive(active);
        user.setPerson(person);
        user.setParentUsers(parentUsers);
        user.setChildUsers(childUsers);
        user.setRoles(roles);
        return user;
    }
}
