package edu.wmi.dpri.przychodnia.commons.usermanagement.builder;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.IdTypeWebModel;

/**
 * Created by kmazu on 06.07.2016.
 */
public final class IdTypeWebModelBuilder {
    private Integer id;
    private String name;

    private IdTypeWebModelBuilder() {
    }

    public static IdTypeWebModelBuilder anIdTypeWebModel() {
        return new IdTypeWebModelBuilder();
    }

    public IdTypeWebModelBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public IdTypeWebModelBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public IdTypeWebModel build() {
        IdTypeWebModel idTypeWebModel = new IdTypeWebModel();
        idTypeWebModel.setId(id);
        idTypeWebModel.setName(name);
        return idTypeWebModel;
    }
}
