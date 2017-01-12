package edu.wmi.dpri.przychodnia.commons.usermanagement.builder;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.SexWebModel;


public final class SexWebModelBuilder {
    private Long id;
    private String name;

    private SexWebModelBuilder() {
    }

    public static SexWebModelBuilder aSexWebModel() {
        return new SexWebModelBuilder();
    }

    public SexWebModelBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public SexWebModelBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public SexWebModel build() {
        SexWebModel sexWebModel = new SexWebModel();
        sexWebModel.setId(id);
        sexWebModel.setName(name);
        return sexWebModel;
    }
}
