package edu.wmi.dpri.przychodnia.server.usermanagement.web.service;

import edu.wmi.dpri.przychodnia.server.entity.Person;
import edu.wmi.dpri.przychodnia.server.usermanagement.function.UserRegisteringStateFunctions;
import edu.wmi.dpri.przychodnia.server.usermanagement.service.PersonService;
import edu.wmi.dpri.przychodnia.server.usermanagement.state.UserRegisteringState;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by lupus on 18.10.16.
 */
@Service
public class PersonWebService {

    @Inject
    private PersonService personService;
    @Inject
    private UserRegisteringStateFunctions userRegisteringStateFunctions;

    public void handleAddingPersonDuringRegistration(UserRegisteringState state) {
        Person person = userRegisteringStateFunctions.createPersonToSaveFromState(state);
        Person savedPerson = personService.createPerson(person, person.getPESEL());
        state.setSavedPerson(savedPerson);
    }


}
