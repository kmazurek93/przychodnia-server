package edu.wmi.dpri.przychodnia.server.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by khartv on 17.05.2016.
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", nullable = false, unique = true, length = 50)
    private String login;

    @Column(name = "password", nullable = false, length = 1000)
    private String password;

    @Column(name = "email_address", unique = true, nullable = false, length = 100)
    private String emailAddress;

    @Column(name = "active")
    private Boolean active;

    @ManyToOne(targetEntity = Person.class)
    @JoinColumn(name = "pesel")
    private Person person;

    @ManyToMany
    @JoinTable(name = "user_connection", joinColumns = @JoinColumn(name = "parent_id"),
            inverseJoinColumns = @JoinColumn(name = "child_id"))
    private Set<User> parentUsers;

    @ManyToMany
    @JoinTable(name = "user_connection", joinColumns = @JoinColumn(name = "child_id"),
            inverseJoinColumns = @JoinColumn(name = "parent_id"))
    private Set<User> childUsers;

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

    public Set<User> getParentUsers() {
        return parentUsers;
    }

    public void setParentUsers(Set<User> parentUsers) {
        this.parentUsers = parentUsers;
    }

    public Set<User> getChildUsers() {
        return childUsers;
    }

    public void setChildUsers(Set<User> childUsers) {
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

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getActive() {
        return active;
    }
}
