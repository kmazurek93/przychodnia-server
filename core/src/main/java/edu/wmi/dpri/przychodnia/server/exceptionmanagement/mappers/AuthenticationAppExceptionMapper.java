package edu.wmi.dpri.przychodnia.server.exceptionmanagement.mappers;

import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.auth.AuthenticationAppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * Created by lupus on 22.10.16.
 */
public class AuthenticationAppExceptionMapper implements ExceptionMapper<AuthenticationAppException> {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationAppException.class);

    @Override
    public Response toResponse(AuthenticationAppException ex) {
        logger.error("Authentication exception: ", ex);
        return Response.status(ex.getErrorMessage().getStatus())
                .entity(ex.getErrorMessage())
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
