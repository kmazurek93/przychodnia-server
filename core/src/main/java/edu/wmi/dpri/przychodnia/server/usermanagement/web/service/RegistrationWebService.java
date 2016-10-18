package edu.wmi.dpri.przychodnia.server.usermanagement.web.service;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.RegistrationOutput;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.creation.RegistrationInputWebModel;
import edu.wmi.dpri.przychodnia.server.usermanagement.state.UserRegisteringState;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

import static edu.wmi.dpri.przychodnia.server.usermanagement.state.UserRegisteringStateBuilder.anUserRegisteringState;

/**
 * Created by lupus on 18.10.16.
 */
@Component
public class RegistrationWebService {

    @Inject
    private AddressWebService addressWebService;
    @Inject
    private PersonWebService personWebService;
    @Inject
    private UserWebService userWebService;

    public RegistrationOutput registerUserAndReturnOutput(RegistrationInputWebModel input) {
        UserRegisteringState state = anUserRegisteringState().withRegistrationInputWebModel
                (input).build();
        addressWebService.handleAddingAddressesDuringRegistration(state);
        personWebService.handleAddingPersonDuringRegistration(state);
        userWebService.handleAddingUserDuringRegistration(state);
        //TODO create registration output
        return null;

    }
}
