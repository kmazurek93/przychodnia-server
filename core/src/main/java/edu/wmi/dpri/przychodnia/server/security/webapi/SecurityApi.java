package edu.wmi.dpri.przychodnia.server.security.webapi;

import edu.wmi.dpri.przychodnia.server.security.jwt.model.JwtToken;
import edu.wmi.dpri.przychodnia.server.security.webmodel.UserContextWebModel;

import javax.ws.rs.*;
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
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    JwtToken refreshToken(RefreshTokenModel model);

    @Path(USER_CONTEXT)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    UserContextWebModel getUserContext();
}
