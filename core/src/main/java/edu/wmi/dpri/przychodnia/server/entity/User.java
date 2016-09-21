package edu.wmi.dpri.przychodnia.server.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by khartv on 17.05.2016.
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email_address", unique = true, nullable = false)
    private String emailAddress;

    @Column(name = "active")
    private boolean active;

    @ManyToOne(targetEntity = Person.class)
    @JoinColumn(name = "pesel")
    private Person person;

    @ManyToMany
    @JoinTable(name = "user_connection", joinColumns = @JoinColumn(name = "parent_id"),
            inverseJoinColumns = @JoinColumn(name = "child_id"))
    private List<User> parentUsers;

    @ManyToMany
    @JoinTable(name = "user_connection", joinColumns = @JoinColumn(name = "child_id"),
            inverseJoinColumns = @JoinColumn(name = "parent_id"))
    private List<User> childUsers;

    @ManyToMany
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    public User() {
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<User> getParentUsers() {
        return parentUsers;
    }

    public void setParentUsers(List<User> parentUsers) {
        this.parentUsers = parentUsers;
    }

    public List<User> getChildUsers() {
        return childUsers;
    }

    public void setChildUsers(List<User> childUsers) {
        this.childUsers = childUsers;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
