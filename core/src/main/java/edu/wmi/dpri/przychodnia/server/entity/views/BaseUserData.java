package edu.wmi.dpri.przychodnia.server.entity.views;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Created by khartv on 30.11.2016.
 */

@ToString
@NoArgsConstructor
@MappedSuperclass
public class BaseUserData {

    @Column(name = "login")
    @Getter String login;

    @Column(name = "email_address")
    @Getter String email;

    @Column(name = "pesel")
    @Getter String pesel;

    @Column(name = "user_id")
    @Getter Long userId;

    @Column(name = "address")
    @Getter String address;

    @Column(name = "mailing_address")
    @Getter String mailingAddress;

    @Column(name = "telephone")
    @Getter String telephone;
}
