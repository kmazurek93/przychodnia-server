package edu.wmi.dpri.przychodnia.server.exceptionmanagement.mappers;


import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Set;


@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
    private static final Logger logger = LoggerFactory.getLogger(ConstraintViolationExceptionMapper.class);
    @Override
    public Response toResponse(ConstraintViolationException ex) {
        ErrorMessage errorMessage = new ErrorMessage();
        setHttpStatus(ex, errorMessage);
        Set<ConstraintViolation<?>> set = ex.getConstraintViolations();
        String message = "";
        logger.error("Constraint violation exception :\n", ex);
        for(ConstraintViolation<?> c : set) {
                String templ = "";
                templ += c.getMessage() + ": ";
                templ += c.getPropertyPath().toString();
                message += templ + " \n ";

        }
        errorMessage.setMessage(message);

        return Response.status(errorMessage.getStatus())
                .entity(errorMessage)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    private void setHttpStatus(ConstraintViolationException ex, ErrorMessage errorMessage) {

        errorMessage.setStatus(Response.Status.BAD_REQUEST.getStatusCode());

    }
}
