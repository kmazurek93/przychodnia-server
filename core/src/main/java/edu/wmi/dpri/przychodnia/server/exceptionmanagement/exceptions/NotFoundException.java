package edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions;

/**
 * Created by lupus on 18.06.16.
 */

public class NotFoundException extends AppException {


    public NotFoundException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
