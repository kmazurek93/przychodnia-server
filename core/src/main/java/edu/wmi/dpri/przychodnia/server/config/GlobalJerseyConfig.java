package edu.wmi.dpri.przychodnia.server.config;

import com.fasterxml.jackson.jaxrs.base.JsonMappingExceptionMapper;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.mappers.*;
import edu.wmi.dpri.przychodnia.server.security.webapi.SecurityApiImpl;
import edu.wmi.dpri.przychodnia.server.usermanagement.web.api.*;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created by kmazu on 07.09.2016.
 */
@Configuration
@Component
public class GlobalJerseyConfig extends ResourceConfig {

    public GlobalJerseyConfig() {
        //apis
        //user management
        register(IdTypeManagementWebApiImpl.class);
        register(AddressWebApiImpl.class);
        register(PublicWebApiImpl.class);
        register(UserConnectionWebApiImpl.class);
        register(UserWebApiImpl.class);
        register(RoleManagementWebApiImpl.class);
        //security
        register(SecurityApiImpl.class);

        //mappers
        register(AppExceptionMapper.class);
        register(GenericExceptionMapper.class);
        register(NotFoundExceptionMapper.class);
        register(JsonMappingExceptionMapper.class);
        register(ConstraintViolationExceptionMapper.class);
        register(UnsupportedMethodExceptionMapper.class);
        register(AuthenticationAppExceptionMapper.class);

        this.property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        this.property(ServerProperties.BV_DISABLE_VALIDATE_ON_EXECUTABLE_OVERRIDE_CHECK, true);
    }
}
