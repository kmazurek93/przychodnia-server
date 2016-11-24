package edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel;

import javax.validation.constraints.NotNull;

/**
 * Created by lupus on 24.11.16.
 */
public class RoleAssignmentWebModel {

    @NotNull
    private Long userId;
    @NotNull
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getUserId() {

        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
