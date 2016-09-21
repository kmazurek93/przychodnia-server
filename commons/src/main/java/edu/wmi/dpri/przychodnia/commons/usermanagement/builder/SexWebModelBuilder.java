package edu.wmi.dpri.przychodnia.commons.usermanagement.builder;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.SexWebModel;

/**
 * Created by kmazu on 06.07.2016.
 */
public final class SexWebModelBuilder {
    private Integer id;
    private String name;

    private SexWebModelBuilder() {
    }

    public static SexWebModelBuilder aSexWebModel() {
        return new SexWebModelBuilder();
    }

    public SexWebModelBuilder withId(Integer id) {
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
