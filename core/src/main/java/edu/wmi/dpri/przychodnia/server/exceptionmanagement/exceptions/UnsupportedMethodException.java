package edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions;


public class UnsupportedMethodException extends AppException {
    public UnsupportedMethodException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
