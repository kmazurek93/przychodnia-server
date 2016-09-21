package edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions;

/**
 * Created by lupus on 18.06.16.
 */
public class ConflictException extends AppException {


    public ConflictException(ErrorMessage errorMessage) {
        super(errorMessage);
    }

}
