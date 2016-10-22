package edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.auth;

import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.ErrorMessage;
import org.springframework.security.core.AuthenticationException;


/**
 * Created by lupus on 22.10.16.
 */
public class AuthenticationAppException extends AuthenticationException {
    private static final long serialVersionUID = -85599334534387947L;

    private ErrorMessage errorMessage;

    public AuthenticationAppException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;

    }
}
