package edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions;

/**
 * Created by lupus on 22.10.16.
 */
public class UnsupportedMethodException extends AppException {
    public UnsupportedMethodException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
