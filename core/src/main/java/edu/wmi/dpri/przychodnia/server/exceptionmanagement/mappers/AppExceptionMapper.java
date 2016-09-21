package edu.wmi.dpri.przychodnia.server.exceptionmanagement.mappers;


import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.AppException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by lupus on 10.05.16.
 */
@Provider
public class AppExceptionMapper implements ExceptionMapper<AppException> {

    public Response toResponse(AppException ex) {
        return Response.status(ex.getErrorMessage().getStatus())
                .entity(ex.getErrorMessage())
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}


