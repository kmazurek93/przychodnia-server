package edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.auth;

import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.ErrorMessage;


public class ForbiddenException extends AuthenticationAppException {

    public ForbiddenException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
