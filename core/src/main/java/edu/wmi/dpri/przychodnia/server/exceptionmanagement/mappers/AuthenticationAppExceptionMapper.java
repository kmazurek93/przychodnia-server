package edu.wmi.dpri.przychodnia.server.exceptionmanagement.mappers;

import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.auth.AuthenticationAppException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;


public class AuthenticationAppExceptionMapper implements ExceptionMapper<AuthenticationAppException> {

    @Override
    public Response toResponse(AuthenticationAppException ex) {
        return Response.status(ex.getErrorMessage().getStatus())
                .entity(ex.getErrorMessage())
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
