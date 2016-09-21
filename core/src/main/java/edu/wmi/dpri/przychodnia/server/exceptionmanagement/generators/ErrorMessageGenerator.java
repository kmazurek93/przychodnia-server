package edu.wmi.dpri.przychodnia.server.exceptionmanagement.generators;


import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.ErrorMessage;

/**
 * Created by kmazu on 19.06.2016.
 */
public class ErrorMessageGenerator {

    public static ErrorMessage getConflictMessage(String id, String type) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(type + " with id " + id + " already exists");
        errorMessage.setCode(409);
        errorMessage.setStatus(409);
        errorMessage.setLink(null);
        return errorMessage;
    }

    public static ErrorMessage getNotFoundErrorMessage(String id, String type) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(type + " with id " + id + " not found");
        errorMessage.setCode(404);
        errorMessage.setStatus(404);
        errorMessage.setLink(null);
        return errorMessage;
    }
}
