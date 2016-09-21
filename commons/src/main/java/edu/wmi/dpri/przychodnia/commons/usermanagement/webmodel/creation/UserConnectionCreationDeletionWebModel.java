package edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.creation;

import javax.validation.constraints.NotNull;

/**
 * Created by kmazu on 06.07.2016.
 */
public class UserConnectionCreationDeletionWebModel {

    @NotNull
    private Integer parentUserId;
    @NotNull
    private Integer childUserId;


    public Integer getParentUserId() {
        return parentUserId;
    }

    public void setParentUserId(Integer parentUserId) {
        this.parentUserId = parentUserId;
    }

    public Integer getChildUserId() {
        return childUserId;
    }

    public void setChildUserId(Integer childUserId) {
        this.childUserId = childUserId;
    }
}
