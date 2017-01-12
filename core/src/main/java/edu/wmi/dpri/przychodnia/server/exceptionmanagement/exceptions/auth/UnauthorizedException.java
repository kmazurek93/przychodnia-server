package edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.auth;

import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.ErrorMessage;


public class UnauthorizedException extends AuthenticationAppException {

    public UnauthorizedException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
