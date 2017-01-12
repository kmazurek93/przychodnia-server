package edu.wmi.dpri.przychodnia.server.usermanagement.service.verification;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.AddressWebModel;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.PersonalDataWebModel;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.UserCrudWebModel;
import edu.wmi.dpri.przychodnia.server.entity.Address;
import edu.wmi.dpri.przychodnia.server.entity.Person;
import edu.wmi.dpri.przychodnia.server.entity.User;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.ErrorMessage;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.auth.ForbiddenException;
import edu.wmi.dpri.przychodnia.server.usermanagement.service.UserService;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;

import static edu.wmi.dpri.przychodnia.server.exceptionmanagement.generators.ErrorMessageGenerator.getForbiddenErrorMessage;
import static edu.wmi.dpri.przychodnia.server.security.model.RoleAuthority.STAFF_OR_ADMIN;


@Component
public class UserEntityVerificationUtility {

    @Inject
    private UserService userService;
    @Inject
    private UserVerificationService userVerificationService;

    public void verifyIfWantsToUpdateCorrectEntities(UserCrudWebModel userCrudWebModel, Long pathUserId) {
        verifyIfHasRights(pathUserId, userCrudWebModel.getUserData().getId());
        User userById = userService.getUserById(pathUserId);
        verifyIfChildEntitiesIdsAreMatching(userById, userCrudWebModel);
    }

    private void verifyIfHasRights(Long pathUserId, Long modelId) {
        boolean isUserIdCorrect = userVerificationService.verifyIfIdIsEqual(pathUserId);
        boolean isModelIdCorrect = userVerificationService.verifyIfIdIsEqual(modelId);
        boolean isAdmin = userVerificationService.verifyIfHasAnyAuthorityOf(STAFF_OR_ADMIN);
        if (!(isModelIdCorrect && isUserIdCorrect)) {
            if (!isAdmin) {
                if (pathUserId.equals(modelId)) {
                    ErrorMessage errorMessage =
                            getForbiddenErrorMessage("INSUFFICIENT_PRIVILEGES");
                    throw new ForbiddenException(errorMessage);
                } else {
                    throw new BadRequestException("IDS_INVALID");
                }
            }
        }
    }

    private void verifyIfChildEntitiesIdsAreMatching(User user, UserCrudWebModel userCrudWebModel) {
        Person person = user.getPerson();
        Address address = person.getAddress();
        Address mailingAddress = person.getMailingAddress();
        throwExceptionIfPeselDoesNotMatch(person, userCrudWebModel.getPersonalData());
        throwExceptionIfAddressIdDoesNotMach(address, userCrudWebModel.getAddress());
        throwExceptionIfMailingAddressIdDoesNotMach(mailingAddress, userCrudWebModel.getMailingAddress());
    }

    private void throwExceptionIfPeselDoesNotMatch(Person person, PersonalDataWebModel personalData) {
        if (!person.getPESEL().equals(personalData.getPesel())) {
            ErrorMessage errorMessage = getForbiddenErrorMessage("IDS_INVALID");
            throw new ForbiddenException(errorMessage);
        }
    }

    private void throwExceptionIfAddressIdDoesNotMach(Address address, AddressWebModel model) {
        if (!address.getId().equals(model.getId())) {
            ErrorMessage errorMessage = getForbiddenErrorMessage("IDS_INVALID");
            throw new ForbiddenException(errorMessage);
        }
    }

    private void throwExceptionIfMailingAddressIdDoesNotMach(Address mailingAddress, AddressWebModel model) {
        if (mailingAddress != null) {
            throwExceptionIfAddressIdDoesNotMach(mailingAddress, model);
        }
    }
}
