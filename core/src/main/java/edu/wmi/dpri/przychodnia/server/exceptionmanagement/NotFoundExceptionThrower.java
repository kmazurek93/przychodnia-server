package edu.wmi.dpri.przychodnia.server.exceptionmanagement;

import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.ErrorMessage;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.NotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static edu.wmi.dpri.przychodnia.server.exceptionmanagement.generators.ErrorMessageGenerator.getNotFoundErrorMessage;

/**
 * Created by kmazu on 07.09.2016.
 */
public class NotFoundExceptionThrower {

    public static final String ENTITY_OF_CLASS = " of entity of class: ";
    public static final String AND_ID = " and id: ";
    public static final String FAILED = " failed";
    public static final String ID = "ID";
    private static final Logger logger = LoggerFactory.getLogger(NotFoundExceptionThrower.class);

    public static void throwExceptionIfNull(Object id, Object entity, ExceptionCause
            cause, Class entityClazz) {
        if (entity == null) {
            logMessageAndThrowException(id, cause, entityClazz);
        }
    }

    private static void logMessageAndThrowException(Object id, ExceptionCause cause, Class
            entityClazz) {
        logErrorMessage(id, cause, entityClazz);
        ErrorMessage notFoundErrorMessage = getNotFoundErrorMessage(entityClazz.getSimpleName());
        notFoundErrorMessage.setValueType(ID);
        notFoundErrorMessage.setValue(id.toString());
        throw new NotFoundException(notFoundErrorMessage);
    }

    private static void logErrorMessage(Object id, ExceptionCause cause, Class
            entityClazz) {
        String message = StringUtils.join(cause, ENTITY_OF_CLASS,
                entityClazz.getSimpleName(), AND_ID, id, FAILED);
        logger.error(message);
    }

}
