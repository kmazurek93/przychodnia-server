package edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.creation;

import javax.validation.constraints.NotNull;

/**
 * Created by kmazu on 06.07.2016.
 */
public class UserLinkingUnlinkingWebModel {

    @NotNull
    private String parentPesel;
    @NotNull
    private String childPesel;

    public String getParentPesel() {
        return parentPesel;
    }

    public void setParentPesel(String parentPesel) {
        this.parentPesel = parentPesel;
    }

    public String getChildPesel() {
        return childPesel;
    }

    public void setChildPesel(String childPesel) {
        this.childPesel = childPesel;
    }
}
