package edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions;



public class NotFoundException extends AppException {


    public NotFoundException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
