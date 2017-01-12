package edu.wmi.dpri.przychodnia.commons.usermanagement.builder;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.IdTypeWebModel;


public final class IdTypeWebModelBuilder {
    private Long id;
    private String name;

    private IdTypeWebModelBuilder() {
    }

    public static IdTypeWebModelBuilder anIdTypeWebModel() {
        return new IdTypeWebModelBuilder();
    }

    public IdTypeWebModelBuilder withId(Long id) {
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
