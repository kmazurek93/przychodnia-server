package edu.wmi.dpri.przychodnia.server.config;

import com.fasterxml.jackson.jaxrs.base.JsonMappingExceptionMapper;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.mappers.AppExceptionMapper;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.mappers.ConstraintViolationExceptionMapper;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.mappers.GenericExceptionMapper;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.mappers.NotFoundExceptionMapper;
import edu.wmi.dpri.przychodnia.server.usermanagement.web.api.AddressWebApiImpl;
import edu.wmi.dpri.przychodnia.server.usermanagement.web.api.IdTypeManagementWebApiImpl;
import edu.wmi.dpri.przychodnia.server.usermanagement.web.api.RegistrationWebApiImpl;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created by kmazu on 07.09.2016.
 */
@Configuration
@Component
public class UserManagementJerseyConfig extends ResourceConfig {

    public UserManagementJerseyConfig() {
        //apis
        register(IdTypeManagementWebApiImpl.class);
        register(AddressWebApiImpl.class);
        register(RegistrationWebApiImpl.class);
        //mappers
        register(AppExceptionMapper.class);
        register(GenericExceptionMapper.class);
        register(NotFoundExceptionMapper.class);
        register(JsonMappingExceptionMapper.class);
        register(ConstraintViolationExceptionMapper.class);
        this.property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        this.property(ServerProperties.BV_DISABLE_VALIDATE_ON_EXECUTABLE_OVERRIDE_CHECK, true);
    }
}
