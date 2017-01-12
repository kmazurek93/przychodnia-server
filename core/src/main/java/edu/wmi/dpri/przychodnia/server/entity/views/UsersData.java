package edu.wmi.dpri.przychodnia.server.entity.views;

import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@ToString(callSuper = true)
@Entity
@Table(name = "v_users_data")
@Immutable
public class UsersData extends BaseUserData{
    @Id
    @Column(name = "id")
    @Getter Long id;

    public UsersData() {
        super();
    }
}
