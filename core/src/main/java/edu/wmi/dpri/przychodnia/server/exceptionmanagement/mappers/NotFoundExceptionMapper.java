package edu.wmi.dpri.przychodnia.server.exceptionmanagement.mappers;


import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {
    private static final Logger logger = LoggerFactory.getLogger(NotFoundExceptionMapper.class);

    public Response toResponse(NotFoundException ex) {
        logger.error("NotFound: " , ex);
        return Response.status(ex.getResponse().getStatus())
                .entity(new ErrorMessage(ex))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
