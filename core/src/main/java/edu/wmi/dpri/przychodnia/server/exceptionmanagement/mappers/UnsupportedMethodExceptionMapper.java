package edu.wmi.dpri.przychodnia.server.exceptionmanagement.mappers;

import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.ErrorMessage;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.UnsupportedMethodException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * Created by lupus on 22.10.16.
 */
public class UnsupportedMethodExceptionMapper implements
        ExceptionMapper<UnsupportedMethodException>  {
    public Response toResponse(UnsupportedMethodException ex) {
        return Response.status(ex.getResponse().getStatus())
                .entity(new ErrorMessage(ex))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
