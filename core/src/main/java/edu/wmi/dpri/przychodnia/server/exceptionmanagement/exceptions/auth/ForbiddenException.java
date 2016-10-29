package edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.auth;

import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.ErrorMessage;

/**
 * Created by lupus on 29.10.16.
 */
public class ForbiddenException extends AuthenticationAppException {

    public ForbiddenException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
