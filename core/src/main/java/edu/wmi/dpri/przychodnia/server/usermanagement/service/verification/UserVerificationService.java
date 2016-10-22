package edu.wmi.dpri.przychodnia.server.usermanagement.service.verification;

import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.ConflictException;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.ErrorMessage;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.generators.ErrorMessageGenerator;
import edu.wmi.dpri.przychodnia.server.usermanagement.service.PersonService;
import edu.wmi.dpri.przychodnia.server.usermanagement.service.UserService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by lupus on 22.10.16.
 */
@Service
public class UserVerificationService {
    private static final String USERNAME = "USERNAME";
    private static final String EMAIL = "EMAIL";
    private static final String PERSON = "PERSON";
    @Inject
    private UserService userService;
    @Inject
    private PersonService personService;

    public void verifyIfUserNameExists(String username) {
        if (userService.usernameExists(username)) {
            throwExceptionIfTypeExists(USERNAME, username, USERNAME);
        }
    }

    public void verifyIfEmailExists(String email) {
        if (userService.emailExists(email)) {
            throwExceptionIfTypeExists(EMAIL, email, EMAIL);
        }
    }

    public void verifyIfPeselExists(String pesel) {
        if (personService.personExists(pesel)) {
            throwExceptionIfTypeExists(PERSON, pesel, "PESEL");
        }
    }

    public void throwExceptionIfTypeExists(String type, String value, String valueType) {
        ErrorMessage errorMessage = ErrorMessageGenerator
                .getConflictMessage(type, value, valueType);
        throw new ConflictException(errorMessage);
    }
}
