package edu.wmi.dpri.przychodnia.commons.usermanagement.builder;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.RoleWebModel;

/**
 * Created by kmazu on 06.07.2016.
 */
public final class RoleWebModelBuilder {
    private Integer id;
    private String name;

    private RoleWebModelBuilder() {
    }

    public static RoleWebModelBuilder aRoleWebModel() {
        return new RoleWebModelBuilder();
    }

    public RoleWebModelBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public RoleWebModelBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public RoleWebModel build() {
        RoleWebModel roleWebModel = new RoleWebModel();
        roleWebModel.setId(id);
        roleWebModel.setName(name);
        return roleWebModel;
    }
}
