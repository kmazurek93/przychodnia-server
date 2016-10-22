package edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel;

import javax.validation.constraints.NotNull;

/**
 * Created by kmazu on 06.07.2016.
 */
public class SexWebModel {
    private Integer id;
    @NotNull
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
