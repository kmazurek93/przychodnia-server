package edu.wmi.dpri.przychodnia.server.exceptionmanagement.mappers;


import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.AppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider
public class AppExceptionMapper implements ExceptionMapper<AppException> {
    private static final Logger logger = LoggerFactory.getLogger(AppExceptionMapper.class);

    public Response toResponse(AppException ex) {
        logger.error("AppException: ", ex);
        return Response.status(ex.getErrorMessage().getStatus())
                .entity(ex.getErrorMessage())
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}


