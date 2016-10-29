package edu.wmi.dpri.przychodnia.server.usermanagement.web.api;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webapi.UserConnectionWebApi;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.creation.UserLinkingUnlinkingWebModel;
import edu.wmi.dpri.przychodnia.server.usermanagement.web.service.UserConnectionWebService;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

/**
 * Created by lupus on 29.10.16.
 */
@Service
public class UserConnectionWebApiImpl implements UserConnectionWebApi {

    @Inject
    private UserConnectionWebService userConnectionWebService;

    @Override
    public Response connectUsers(UserLinkingUnlinkingWebModel model) {
        userConnectionWebService.connectUsers(model);
        return Response.noContent().build();
    }

    @Override
    public Response removeConnection(UserLinkingUnlinkingWebModel model) {
        userConnectionWebService.removeConnection(model);
        return Response.noContent().build();
    }
}
