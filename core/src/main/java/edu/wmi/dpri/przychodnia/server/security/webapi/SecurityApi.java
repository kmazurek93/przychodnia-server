package edu.wmi.dpri.przychodnia.server.security.webapi;

import edu.wmi.dpri.przychodnia.server.security.jwt.model.JwtToken;
import edu.wmi.dpri.przychodnia.server.security.webmodel.LoginWebModel;
import edu.wmi.dpri.przychodnia.server.security.webmodel.UserContextWebModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import static edu.wmi.dpri.przychodnia.server.security.SecurityConstants.SECURITY_API_PATH;

/**
 * Created by lupus on 22.10.16.
 */
@Path(SECURITY_API_PATH)
public interface SecurityApi {
    String TOKEN_REFRESH_PATH = "/auth/token";
    String USER_CONTEXT = "/userContext";

    @Path(TOKEN_REFRESH_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    JwtToken refreshToken(@Context HttpServletRequest request, @Context HttpServletResponse response);

    @Path(USER_CONTEXT)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    UserContextWebModel getUserContext();
}
