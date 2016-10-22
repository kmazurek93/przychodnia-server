package edu.wmi.dpri.przychodnia.server.usermanagement.state;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.creation.RegistrationInputWebModel;
import edu.wmi.dpri.przychodnia.server.entity.*;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by lupus on 18.10.16.
 */

public class UserRegisteringState {
    private RegistrationInputWebModel registrationInputWebModel;
    private Address savedAddress;
    private Address savedMailingAddress;
    private Person savedPerson;
    private User savedUser;
    private IdType targetIdType;
    private Sex targetSex;
    private List<Role> targetRoles = newArrayList();

    public IdType getTargetIdType() {
        return targetIdType;
    }

    public void setTargetIdType(IdType targetIdType) {
        this.targetIdType = targetIdType;
    }

    public Sex getTargetSex() {
        return targetSex;
    }

    public void setTargetSex(Sex targetSex) {
        this.targetSex = targetSex;
    }

    public List<Role> getTargetRoles() {
        return targetRoles;
    }

    public void setTargetRoles(List<Role> targetRoles) {
        this.targetRoles = targetRoles;
    }

    public RegistrationInputWebModel getRegistrationInputWebModel() {
        return registrationInputWebModel;
    }

    public void setRegistrationInputWebModel(RegistrationInputWebModel registrationInputWebModel) {
        this.registrationInputWebModel = registrationInputWebModel;
    }

    public Address getSavedAddress() {
        return savedAddress;
    }

    public void setSavedAddress(Address savedAddress) {
        this.savedAddress = savedAddress;
    }

    public Address getSavedMailingAddress() {
        return savedMailingAddress;
    }

    public void setSavedMailingAddress(Address savedMailingAddress) {
        this.savedMailingAddress = savedMailingAddress;
    }

    public Person getSavedPerson() {
        return savedPerson;
    }

    public void setSavedPerson(Person savedPerson) {
        this.savedPerson = savedPerson;
    }

    public User getSavedUser() {
        return savedUser;
    }

    public void setSavedUser(User savedUser) {
        this.savedUser = savedUser;
    }
}
