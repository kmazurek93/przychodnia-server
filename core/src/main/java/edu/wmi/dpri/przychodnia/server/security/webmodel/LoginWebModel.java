package edu.wmi.dpri.przychodnia.server.security.webmodel;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * Created by lupus on 22.10.16.
 */
public class LoginWebModel {
    @NotNull
    private String username;
    @NotNull
    private String password;

    public LoginWebModel() {
    }

    public LoginWebModel(@JsonProperty("username") String username,
                         @JsonProperty("password") String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
