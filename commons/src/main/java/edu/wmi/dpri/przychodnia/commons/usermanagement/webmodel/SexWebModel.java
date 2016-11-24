package edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel;

import javax.validation.constraints.NotNull;

/**
 * Created by kmazu on 06.07.2016.
 */
public class SexWebModel {
    private Long id;
    @NotNull
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
