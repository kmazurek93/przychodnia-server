package edu.wmi.dpri.przychodnia.server.usermanagement.service;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.UserCrudWebModel;
import edu.wmi.dpri.przychodnia.server.usermanagement.service.verification.UserEntityVerificationUtility;
import edu.wmi.dpri.przychodnia.server.usermanagement.service.verification.UserVerificationService;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

import static edu.wmi.dpri.przychodnia.server.security.model.RoleAuthority.STAFF_OR_ADMIN;

/**
 * Created by lupus on 18.11.16.
 */
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
        boolean isAdminOrStaff = userVerificationService.verifyIfHasAnyAuthorityOf(STAFF_OR_ADMIN);
        userService.updateUser(userDataWebModel.getUserData(), isAdminOrStaff);
        personService.updatePerson(userDataWebModel.getPersonalData(), isAdminOrStaff);
        addressService.updateAddress(userDataWebModel.getAddress());
        if (userDataWebModel.getMailingAddress() != null) {
            addressService.updateAddress(userDataWebModel.getMailingAddress());
        }
    }
}
