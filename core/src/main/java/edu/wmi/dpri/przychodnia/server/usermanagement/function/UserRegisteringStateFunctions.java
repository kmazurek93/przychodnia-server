package edu.wmi.dpri.przychodnia.server.usermanagement.function;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.PersonalDataWebModel;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.UserDataWebModel;
import edu.wmi.dpri.przychodnia.server.entity.Person;
import edu.wmi.dpri.przychodnia.server.entity.User;
import edu.wmi.dpri.przychodnia.server.usermanagement.service.PasswordService;
import edu.wmi.dpri.przychodnia.server.usermanagement.state.UserRegisteringState;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

import static edu.wmi.dpri.przychodnia.server.entity.builder.PersonBuilder.aPerson;
import static edu.wmi.dpri.przychodnia.server.entity.builder.UserBuilder.anUser;

/**
 * Created by lupus on 18.10.16.
 */
@Component
public class UserRegisteringStateFunctions {

    @Inject
    private PasswordService passwordService;

    public Person createPersonToSaveFromState(UserRegisteringState state) {
        Person person = new Person();
        PersonalDataWebModel personalData = getPersonalData(state);
        return createPerson(state, personalData);

    }

    private Person createPerson(UserRegisteringState state, PersonalDataWebModel personalData) {
        return aPerson().withAddress(state.getSavedAddress())
                .withMailingAddress(state.getSavedMailingAddress())
                .withBirthDate(new DateTime(personalData.getBirthDate()))
                .withBirthPlace(personalData.getBirthPlace())
                .withFirstName(personalData.getFirstName())
                .withMiddleName(personalData.getMiddleName())
                .withLastName(personalData.getLastName())
                .withIdNumber(personalData.getIdNumber())
                .withIdType(state.getTargetIdType())
                .withSex(state.getTargetSex())
                .withPESEL(personalData.getPesel())
                .withTelephone(personalData.getTelephone())
                .build();
    }

    private PersonalDataWebModel getPersonalData(UserRegisteringState state) {
        return state.getRegistrationInputWebModel().getPersonalData();
    }

    public User createUserToSaveFromState(UserRegisteringState state) {
        User user = new User();
        UserDataWebModel userDataWebModel = getUserDataWebModel(state);
        return createUser(userDataWebModel, state);
    }

    private User createUser(UserDataWebModel userDataWebModel, UserRegisteringState state) {
        String hashedPassword = passwordService.createSaltedHash(userDataWebModel.getPassword(), userDataWebModel
                .getUsername());
        return anUser()
                .withActive(true)
                .withEmailAddress(userDataWebModel.getEmailAddress())
                .withLogin(userDataWebModel.getUsername())
                .withPassword(hashedPassword)
                .withPerson(state.getSavedPerson())
                .withRoles(state.getTargetRoles())
                .build();
    }

    private UserDataWebModel getUserDataWebModel(UserRegisteringState state) {
        return state.getRegistrationInputWebModel().getUserData();
    }
}
