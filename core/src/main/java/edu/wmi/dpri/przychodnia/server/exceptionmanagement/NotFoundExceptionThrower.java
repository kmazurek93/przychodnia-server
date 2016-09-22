package edu.wmi.dpri.przychodnia.server.exceptionmanagement;

import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.ErrorMessage;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.NotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.ErrorMessageBuilder.anErrorMessage;

/**
 * Created by kmazu on 07.09.2016.
 */
public class NotFoundExceptionThrower {

	private static final Logger logger = LoggerFactory.getLogger(NotFoundExceptionThrower.class);
	public static final String ENTITY_OF_CLASS = " of entity of class: ";
	public static final String AND_ID = " and id: ";
	public static final String FAILED = " failed";

	public static void throwExceptionIfNull(Number id, Object entity, ExceptionCause
			cause,
			Class entityClazz) {
		if (entity == null) {
			String message = StringUtils.join(cause, ENTITY_OF_CLASS, entityClazz.getSimpleName(), AND_ID, id,
					FAILED);
			logger.error(message);
			ErrorMessage errorMessage = anErrorMessage().withMessage(message)
					.withDeveloperMessage(message).withCode(404).withStatus(404).withLink(null)
					.build();
			throw new NotFoundException(errorMessage);
		}
	}

}
