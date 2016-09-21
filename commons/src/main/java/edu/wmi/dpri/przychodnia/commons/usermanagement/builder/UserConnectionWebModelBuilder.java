package edu.wmi.dpri.przychodnia.commons.usermanagement.builder;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.UserConnectionWebModel;

/**
 * Created by kmazu on 06.07.2016.
 */
public final class UserConnectionWebModelBuilder {
    private Integer id;
    private Integer parentId;
    private Integer childId;

    private UserConnectionWebModelBuilder() {
    }

    public static UserConnectionWebModelBuilder anUserConnectionWebModel() {
        return new UserConnectionWebModelBuilder();
    }

    public UserConnectionWebModelBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public UserConnectionWebModelBuilder withParentId(Integer parentId) {
        this.parentId = parentId;
        return this;
    }

    public UserConnectionWebModelBuilder withChildId(Integer childId) {
        this.childId = childId;
        return this;
    }

    public UserConnectionWebModel build() {
        UserConnectionWebModel userConnectionWebModel = new UserConnectionWebModel();
        userConnectionWebModel.setId(id);
        userConnectionWebModel.setParentId(parentId);
        userConnectionWebModel.setChildId(childId);
        return userConnectionWebModel;
    }
}
