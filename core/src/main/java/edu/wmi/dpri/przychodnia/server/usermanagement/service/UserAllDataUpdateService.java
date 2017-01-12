package edu.wmi.dpri.przychodnia.server.usermanagement.service;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.UserCrudWebModel;
import edu.wmi.dpri.przychodnia.server.usermanagement.service.verification.UserEntityVerificationUtility;
import edu.wmi.dpri.przychodnia.server.usermanagement.service.verification.UserVerificationService;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

import static edu.wmi.dpri.przychodnia.server.security.model.RoleAuthority.ROLE_ADMIN;


@Component
public class UserAllDataUpdateService {

    @Inject
    private UserService userService;
    @Inject
    private PersonService personService;
    @Inject
    private AddressService addressService;
    @Inject
    private UserEntityVerificationUtility verificationUtility;
    @Inject
    private UserVerificationService userVerificationService;


    public void updateUserData(Long pathId, UserCrudWebModel userDataWebModel) {
        verificationUtility.verifyIfWantsToUpdateCorrectEntities(userDataWebModel, pathId);
        boolean isAdmin = userVerificationService.verifyIfHasAuthority(ROLE_ADMIN);
        userService.updateUser(userDataWebModel.getUserData(), isAdmin);
        personService.updatePerson(userDataWebModel.getPersonalData(), isAdmin);
        addressService.updateAddress(userDataWebModel.getAddress());
        if (userDataWebModel.getMailingAddress() != null) {
            addressService.createOrUpdateMailingAddress(userDataWebModel.getMailingAddress());
        }
    }
}
