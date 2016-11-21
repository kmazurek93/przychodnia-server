package edu.wmi.dpri.przychodnia.server.usermanagement.service.model;

import java.util.Set;

/**
 * Created by lupus on 21.11.16.
 */
public class SetsForSearch {
    private Set<Long> byEmail;
    private Set<Long> byAddress;
    private Set<Long> byPerson;
    private Set<Long> byRole;

    public Set<Long> getByEmail() {
        return byEmail;
    }

    public void setByEmail(Set<Long> byEmail) {
        this.byEmail = byEmail;
    }

    public Set<Long> getByAddress() {
        return byAddress;
    }

    public void setByAddress(Set<Long> byAddress) {
        this.byAddress = byAddress;
    }

    public Set<Long> getByPerson() {
        return byPerson;
    }

    public void setByPerson(Set<Long> byPerson) {
        this.byPerson = byPerson;
    }

    public Set<Long> getByRole() {
        return byRole;
    }

    public void setByRole(Set<Long> byRole) {
        this.byRole = byRole;
    }
}
