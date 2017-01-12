package edu.wmi.dpri.przychodnia.client.rest.usermanagement;

import edu.wmi.dpri.przychodnia.commons.rest.GenericRestClient;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webapi.UserConnectionWebApi;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.creation.UserLinkingUnlinkingWebModel;

import javax.ws.rs.core.Response;

public class UserConnectionWebApiClient extends GenericRestClient<UserConnectionWebApi> {
    public UserConnectionWebApiClient(String url) {
        super(url, UserConnectionWebApi.class);
    }

    public Response connectUsers(UserLinkingUnlinkingWebModel model) {
        return resource.connectUsers(model);
    }

    public Response removeConnection(UserLinkingUnlinkingWebModel model) {
        return resource.removeConnection(model);
    }
}
