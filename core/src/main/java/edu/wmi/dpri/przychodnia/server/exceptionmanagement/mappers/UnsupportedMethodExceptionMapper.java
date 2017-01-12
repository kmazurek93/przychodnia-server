package edu.wmi.dpri.przychodnia.server.exceptionmanagement.mappers;

import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.ErrorMessage;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.UnsupportedMethodException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;


public class UnsupportedMethodExceptionMapper implements
        ExceptionMapper<UnsupportedMethodException>  {
    private static final Logger logger = LoggerFactory.getLogger(UnsupportedMethodExceptionMapper.class);

    public Response toResponse(UnsupportedMethodException ex) {
        logger.error("UnsupportedMethod Exception: ", ex);
        return Response.status(ex.getResponse().getStatus())
                .entity(new ErrorMessage(ex))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
