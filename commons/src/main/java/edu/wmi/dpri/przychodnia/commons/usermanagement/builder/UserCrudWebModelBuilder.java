package edu.wmi.dpri.przychodnia.commons.usermanagement.builder;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.AddressWebModel;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.PersonalDataWebModel;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.UserCrudWebModel;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.UserDataWebModel;

/**
 * Created by lupus on 14.11.16.
 */
public final class UserCrudWebModelBuilder {
    private AddressWebModel address;
    private AddressWebModel mailingAddress;
    private PersonalDataWebModel personalData;
    private UserDataWebModel userData;

    private UserCrudWebModelBuilder() {
    }

    public static UserCrudWebModelBuilder anUserCrudWebModel() {
        return new UserCrudWebModelBuilder();
    }

    public UserCrudWebModelBuilder withAddress(AddressWebModel address) {
        this.address = address;
        return this;
    }

    public UserCrudWebModelBuilder withMailingAddress(AddressWebModel mailingAddress) {
        this.mailingAddress = mailingAddress;
        return this;
    }

    public UserCrudWebModelBuilder withPersonalData(PersonalDataWebModel personalData) {
        this.personalData = personalData;
        return this;
    }

    public UserCrudWebModelBuilder withUserData(UserDataWebModel userData) {
        this.userData = userData;
        return this;
    }

    public UserCrudWebModel build() {
        UserCrudWebModel userCrudWebModel = new UserCrudWebModel();
        userCrudWebModel.setAddress(address);
        userCrudWebModel.setMailingAddress(mailingAddress);
        userCrudWebModel.setPersonalData(personalData);
        userCrudWebModel.setUserData(userData);
        return userCrudWebModel;
    }
}
