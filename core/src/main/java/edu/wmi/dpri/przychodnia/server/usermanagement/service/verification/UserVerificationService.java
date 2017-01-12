package edu.wmi.dpri.przychodnia.server.usermanagement.service.verification;

import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.ConflictException;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.ErrorMessage;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.generators.ErrorMessageGenerator;
import edu.wmi.dpri.przychodnia.server.security.model.UserContext;
import edu.wmi.dpri.przychodnia.server.usermanagement.service.PersonService;
import edu.wmi.dpri.przychodnia.server.usermanagement.service.UserService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserVerificationService {
    private static final String USERNAME = "USERNAME";
    private static final String EMAIL = "EMAIL";
    private static final String PERSON = "PERSON";
    public static final String INSUFFICIENT_PRIVILEGES = "INSUFFICIENT_PRIVILEGES";
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

    public boolean verifyIfHasAuthority(String roleName) {
        List<String> assignedRoles = getAssignedRolesAsStringList();
        return assignedRoles.contains(roleName);
    }

    public boolean verifyIfHasAnyAuthorityOf(List<String> roles) {
        List<String> assignedRoles = getAssignedRolesAsStringList();
        return !CollectionUtils.intersection(assignedRoles, roles).isEmpty();
    }

    @SuppressWarnings("unchecked")
    private List<String> getAssignedRolesAsStringList() {
        Authentication authentication = getUserContextFromSecurityContextHolder();
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();
        return authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }

    private Authentication getUserContextFromSecurityContextHolder() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public boolean verifyIfIdIsEqual(Long userId) {
        Authentication authentication = getUserContextFromSecurityContextHolder();
        UserContext principal = (UserContext) authentication.getPrincipal();
        return principal.getUserId().equals(userId);
    }
}
