package edu.wmi.dpri.przychodnia.server.usermanagement.function;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.PersonalDataWebModel;
import edu.wmi.dpri.przychodnia.server.entity.Person;
import edu.wmi.dpri.przychodnia.server.usermanagement.state.UserRegisteringState;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import static edu.wmi.dpri.przychodnia.server.entity.builder.PersonBuilder.aPerson;

/**
 * Created by lupus on 18.10.16.
 */
@Component
public class UserRegisteringStateFunctions {

    public Person createPersonToSaveFromState(UserRegisteringState state) {
        Person person = new Person();
        PersonalDataWebModel personalData = getPersonalData(state);
        person.setAddress(state.getSavedAddress());
        person.setMailingAddress(state.getSavedMailingAddress());
        person.setBirthDate(new DateTime(personalData.getBirthDate()));
        person.setBirthPlace(personalData.getBirthPlace());
        aPerson().withAddress(state.getSavedAddress())
                .withMailingAddress(state.getSavedMailingAddress())
                .withBirthDate(new DateTime(personalData.getBirthDate()))
                .withBirthPlace(personalData.getBirthPlace())
                .withFirstName(personalData.getFirstName())
                .withMiddleName(personalData.getMiddleName())
                .withLastName(personalData.getLastName())
                .withIdNumber(personalData.getIdNumber())

    }

    private PersonalDataWebModel getPersonalData(UserRegisteringState state) {
        return state.getRegistrationInputWebModel().getPersonalData();
    }
}
