package edu.wmi.dpri.przychodnia.server.security.jwt.model;

/**
 * Created by lupus on 22.10.16.
 */
public enum Scopes {
    REFRESH_TOKEN;

    public String authority() {
        return "ROLE_" + this.name();
    }
}
