package edu.wmi.dpri.przychodnia.server.usermanagement.service;

import org.junit.Test;

import java.util.Base64;

import static java.lang.System.out;


public class PasswordServiceTest {
    private PasswordService tested = new PasswordService();
    @Test
    public void isLoginValid() throws Exception {

    }

    @Test
    public void createSaltedHash() throws Exception {
        String username = "admin";
        String passwd = "2xglcah1";
        Base64.Encoder encoder = Base64.getEncoder();
        String passwdEncoded = encoder.encodeToString(passwd.getBytes());
        out.println(passwdEncoded);
        String saltedHash = tested.createSaltedHash(passwdEncoded, username);
        out.println(saltedHash);
    }

}