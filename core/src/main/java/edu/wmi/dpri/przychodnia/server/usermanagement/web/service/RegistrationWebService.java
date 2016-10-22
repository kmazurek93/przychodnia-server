package edu.wmi.dpri.przychodnia.server.usermanagement.web.service;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.RegistrationOutput;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.creation.RegistrationInputWebModel;
import edu.wmi.dpri.przychodnia.server.usermanagement.function.AddressToAddressWebModelFunction;
import edu.wmi.dpri.przychodnia.server.usermanagement.service.verification.UserVerificationService;
import edu.wmi.dpri.przychodnia.server.usermanagement.state.UserRegisteringState;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

import static edu.wmi.dpri.przychodnia.commons.usermanagement.UserManagementConstants.DEFAULT_ROLES_FOR_NEW_USER;

/**
 * Created by lupus on 18.10.16.
 */
@Service
public class RegistrationWebService {

    @Inject
    private AddressWebService addressWebService;
    @Inject
    private PersonWebService personWebService;
    @Inject
    private UserWebService userWebService;
    @Inject
    private UserRegisteringStatePreparer userRegisteringStatePreparer;
    @Inject
    private AddressToAddressWebModelFunction addressFunction;
    @Inject
    private UserVerificationService userVerificationService;

    public RegistrationOutput registerUserAndReturnOutput(RegistrationInputWebModel input) {
        verifyIfProvidedEmailAndUsernameAndPeselAreUnique(input);
        UserRegisteringState state = userRegisteringStatePreparer.prepareUserRegisteringState
                (input);
        handleRegistrationProcess(state);
        return createRegistrationOutput(input, state);

    }

    private void verifyIfProvidedEmailAndUsernameAndPeselAreUnique(RegistrationInputWebModel input) {
        userVerificationService.verifyIfEmailExists(input.getUserData().getEmailAddress());
        userVerificationService.verifyIfUserNameExists(input.getUserData().getUsername());
        userVerificationService.verifyIfPeselExists(input.getPersonalData().getPesel());
    }

    private void handleRegistrationProcess(UserRegisteringState state) {
        addressWebService.handleAddingAddressesDuringRegistration(state);
        personWebService.handleAddingPersonDuringRegistration(state);
        userWebService.handleAddingUserDuringRegistration(state);
    }

    private RegistrationOutput createRegistrationOutput(RegistrationInputWebModel input, UserRegisteringState state) {
        RegistrationOutput registrationOutput = new RegistrationOutput();
        registrationOutput.setPersonalDataWebModel(input.getPersonalData());
        registrationOutput.setUserDataWebModel(input.getUserData());
        registrationOutput.setAddress(addressFunction.apply(state.getSavedAddress()));
        registrationOutput.setMailingAddress(addressFunction.apply(state.getSavedMailingAddress()));
        registrationOutput.getUserDataWebModel().setPassword(state.getSavedUser().getPassword());
        registrationOutput.getUserDataWebModel().setRoles(DEFAULT_ROLES_FOR_NEW_USER);
        return registrationOutput;
    }
}
