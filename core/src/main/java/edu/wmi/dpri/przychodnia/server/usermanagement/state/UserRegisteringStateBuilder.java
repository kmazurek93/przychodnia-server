package edu.wmi.dpri.przychodnia.server.usermanagement.state;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.creation.RegistrationInputWebModel;
import edu.wmi.dpri.przychodnia.server.entity.Address;
import edu.wmi.dpri.przychodnia.server.entity.Person;
import edu.wmi.dpri.przychodnia.server.entity.User;

/**
 * Created by lupus on 18.10.16.
 */
public final class UserRegisteringStateBuilder {
    private RegistrationInputWebModel registrationInputWebModel;
    private Address savedAddress;
    private Address savedMailingAddress;
    private Person savedPerson;
    private User savedUser;

    private UserRegisteringStateBuilder() {
    }

    public static UserRegisteringStateBuilder anUserRegisteringState() {
        return new UserRegisteringStateBuilder();
    }

    public UserRegisteringStateBuilder withRegistrationInputWebModel(RegistrationInputWebModel registrationInputWebModel) {
        this.registrationInputWebModel = registrationInputWebModel;
        return this;
    }

    public UserRegisteringStateBuilder withSavedAddress(Address savedAddress) {
        this.savedAddress = savedAddress;
        return this;
    }

    public UserRegisteringStateBuilder withSavedMailingAddress(Address savedMailingAddress) {
        this.savedMailingAddress = savedMailingAddress;
        return this;
    }

    public UserRegisteringStateBuilder withSavedPerson(Person savedPerson) {
        this.savedPerson = savedPerson;
        return this;
    }

    public UserRegisteringStateBuilder withSavedUser(User savedUser) {
        this.savedUser = savedUser;
        return this;
    }

    public UserRegisteringState build() {
        UserRegisteringState userRegisteringState = new UserRegisteringState();
        userRegisteringState.setRegistrationInputWebModel(registrationInputWebModel);
        userRegisteringState.setSavedAddress(savedAddress);
        userRegisteringState.setSavedMailingAddress(savedMailingAddress);
        userRegisteringState.setSavedPerson(savedPerson);
        userRegisteringState.setSavedUser(savedUser);
        return userRegisteringState;
    }
}
