package edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by lupus on 11.11.16.
 */
public class UserCrudWebModel {
    @NotNull
    @Valid
    private AddressWebModel address;
    private AddressWebModel mailingAddress;
    @NotNull
    @Valid
    private PersonalDataWebModel personalData;
    @NotNull
    @Valid
    private UserDataWebModel userData;

    public AddressWebModel getAddress() {
        return address;
    }

    public void setAddress(AddressWebModel address) {
        this.address = address;
    }

    public AddressWebModel getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(AddressWebModel mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public PersonalDataWebModel getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalDataWebModel personalData) {
        this.personalData = personalData;
    }

    public UserDataWebModel getUserData() {
        return userData;
    }

    public void setUserData(UserDataWebModel userData) {
        this.userData = userData;
    }
}
