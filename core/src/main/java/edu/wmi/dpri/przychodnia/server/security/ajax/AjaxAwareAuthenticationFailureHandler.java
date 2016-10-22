package edu.wmi.dpri.przychodnia.server.security.ajax;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.ErrorMessage;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.auth.AuthenticationAppException;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static edu.wmi.dpri.przychodnia.server.exceptionmanagement.generators.ErrorMessageGenerator.getAuthenticationErrorMessage;

/**
 * Created by lupus on 22.10.16.
 */
@Component
@Profile("secure")
public class AjaxAwareAuthenticationFailureHandler implements AuthenticationFailureHandler {
    public static final String AUTH_ERROR = "AUTH_ERROR";
    public static final String BAD_USERNAME_OR_PASSWORD = "BAD_USERNAME_OR_PASSWORD";

    @Inject
    private ObjectMapper mapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException e) throws IOException, ServletException {

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        if (e instanceof BadCredentialsException) {
            ErrorMessage errorMessage = getAuthenticationErrorMessage(BAD_USERNAME_OR_PASSWORD);
            mapper.writeValue(response.getWriter(), errorMessage);
        }
        if (e instanceof AuthenticationAppException) {
            mapper.writeValue(response.getWriter(), ((AuthenticationAppException) e)
                    .getErrorMessage());
        }
        ErrorMessage errorMessage = getAuthenticationErrorMessage(AUTH_ERROR);
        mapper.writeValue(response.getWriter(), errorMessage);

    }
}
