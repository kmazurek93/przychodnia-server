package edu.wmi.dpri.przychodnia.server.entity.views;

import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by khartv on 30.11.2016.
 */
@Entity
@Table(name = "v_users_data")
@Immutable
public class UsersData extends BaseUserData{
    @Id
    @Column(name = "id")
    Long id;

    public UsersData() {
        super();
    }

    public Long getId() {
        return id;
    }
}
