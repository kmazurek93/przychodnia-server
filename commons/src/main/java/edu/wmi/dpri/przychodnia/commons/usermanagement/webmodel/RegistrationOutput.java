package edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel;

/**
 * Created by lupus on 18.10.16.
 */
public class RegistrationOutput {

    private AddressWebModel address;
    private AddressWebModel mailingAddress;
    private PersonalDataWebModel personalDataWebModel;
    private UserDataWebModel userDataWebModel;

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

    public PersonalDataWebModel getPersonalDataWebModel() {
        return personalDataWebModel;
    }

    public void setPersonalDataWebModel(PersonalDataWebModel personalDataWebModel) {
        this.personalDataWebModel = personalDataWebModel;
    }

    public UserDataWebModel getUserDataWebModel() {
        return userDataWebModel;
    }

    public void setUserDataWebModel(UserDataWebModel userDataWebModel) {
        this.userDataWebModel = userDataWebModel;
    }
}
