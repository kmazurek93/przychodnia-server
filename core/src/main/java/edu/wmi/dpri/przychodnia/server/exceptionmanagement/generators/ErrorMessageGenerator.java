package edu.wmi.dpri.przychodnia.server.exceptionmanagement.generators;


import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.ErrorMessage;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by kmazu on 19.06.2016.
 */
public class ErrorMessageGenerator {

    private static final String ALREADY_EXISTS = " already exists";
    private static final String WITH_VALUE = " with value ";
    private static final String WITH_ID = " with id ";

    public static ErrorMessage getConflictMessage(String type, String value, String valueType) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(StringUtils.join(type, "_EXISTS"));
        errorMessage.setCode(409);
        errorMessage.setStatus(409);
        errorMessage.setValue(value);
        errorMessage.setValueType(valueType);
        return errorMessage;
    }

    public static ErrorMessage getNotFoundErrorMessage(String type) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(StringUtils.join(type.toUpperCase(), "_NOT_FOUND"));
        errorMessage.setCode(404);
        errorMessage.setStatus(404);
        return errorMessage;
    }
}
