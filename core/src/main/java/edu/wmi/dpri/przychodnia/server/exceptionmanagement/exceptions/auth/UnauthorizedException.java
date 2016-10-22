package edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.auth;

import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.ErrorMessage;

/**
 * Created by lupus on 22.10.16.
 */
public class UnauthorizedException extends AuthenticationAppException {

    public UnauthorizedException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
