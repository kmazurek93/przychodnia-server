package edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions;
/**
 * Created by lupus on 10.05.16.
 */

import javax.ws.rs.WebApplicationException;

public class AppException extends WebApplicationException {

    private static final long serialVersionUID = -8999932578270387947L;

    private ErrorMessage errorMessage;

    public AppException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }

    public AppException() {

    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;

    }

}
