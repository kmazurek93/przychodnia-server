package edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.creation;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.PersonalDataWebModel;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.UserDataWebModel;

import javax.validation.constraints.NotNull;

/**
 * Created by kmazu on 02.07.2016.
 */
public class RegistrationInputWebModel {
    @NotNull
    private AddressCreationWebModel address;
    private AddressCreationWebModel mailingAddress;
    @NotNull
    private PersonalDataWebModel personalData;
    @NotNull
    private UserDataWebModel userData;

    public AddressCreationWebModel getAddress() {
        return address;
    }

    public void setAddress(AddressCreationWebModel address) {
        this.address = address;
    }

    public AddressCreationWebModel getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(AddressCreationWebModel mailingAddress) {
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
