package edu.wmi.dpri.przychodnia.server.security.jwt.model;


public enum Scopes {
    REFRESH_TOKEN;

    public String authority() {
        return "ROLE_" + this.name();
    }
}
