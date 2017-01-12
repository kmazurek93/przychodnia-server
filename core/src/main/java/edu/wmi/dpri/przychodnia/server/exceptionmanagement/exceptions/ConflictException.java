package edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions;


public class ConflictException extends AppException {


    public ConflictException(ErrorMessage errorMessage) {
        super(errorMessage);
    }

}
