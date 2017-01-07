package edu.wmi.dpri.przychodnia.server.exceptionmanagement.mappers;

import com.fasterxml.jackson.databind.JsonMappingException;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by lupus on 10.05.16.
 */
@Provider
public class JsonMappingExceptionMapper implements ExceptionMapper<JsonMappingException> {
    private static final Logger logger = LoggerFactory.getLogger(JsonMappingExceptionMapper.class);

    @Override

    public Response toResponse(JsonMappingException ex) {
        logger.error("JsonMappingException: ", ex);
        ErrorMessage errorMessage = new ErrorMessage();
        setHttpStatus(ex, errorMessage);
        errorMessage.setMessage(ex.getMessage().split("at")[0]);
        return Response.status(errorMessage.getStatus())
                .entity(errorMessage)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    private void setHttpStatus(JsonMappingException ex, ErrorMessage errorMessage) {

        errorMessage.setStatus(Response.Status.BAD_REQUEST.getStatusCode());

    }
}
