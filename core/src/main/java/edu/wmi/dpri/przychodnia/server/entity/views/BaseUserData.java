package edu.wmi.dpri.przychodnia.server.entity.views;

import javax.persistence.Column;

/**
 * Created by khartv on 30.11.2016.
 */

public class BaseUserData {

    @Column(name = "login")
    String login;

    @Column(name = "email_address")
    String email;

    @Column(name = "pesel")
    String pesel;

    @Column(name = "user_id")
    Long userId;

    @Column(name = "address")
    String address;

    @Column(name = "mailing_address")
    String mailingAddress;

    @Column(name = "telephone")
    String telephone;

    public BaseUserData() {
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getPesel() {
        return pesel;
    }

    public Long getUserId() {
        return userId;
    }

    public String getAddress() {
        return address;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public String getTelephone() {
        return telephone;
    }
}
