package edu.wmi.dpri.przychodnia.server.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by khartv on 17.05.2016.
 */

@Data
@NoArgsConstructor
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
    @Getter(AccessLevel.NONE) private Boolean active;

    @ManyToOne(targetEntity = Person.class)
    @JoinColumn(name = "pesel")
    private Person person;

    @ManyToMany
    @JoinTable(name = "user_connections", joinColumns = @JoinColumn(name = "parent_id"),
            inverseJoinColumns = @JoinColumn(name = "child_id"))
    private Set<User> parentUsers;

    @ManyToMany
    @JoinTable(name = "user_connections", joinColumns = @JoinColumn(name = "child_id"),
            inverseJoinColumns = @JoinColumn(name = "parent_id"))
    private Set<User> childUsers;

    @ManyToMany
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    public Boolean isActive()
    {
        return active;
    }
}
