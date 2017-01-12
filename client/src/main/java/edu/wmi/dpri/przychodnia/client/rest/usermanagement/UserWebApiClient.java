package edu.wmi.dpri.przychodnia.client.rest.usermanagement;

import edu.wmi.dpri.przychodnia.commons.rest.GenericRestClient;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webapi.UserWebApi;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.UserCrudWebModel;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.UserSearchResult;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.UserSearchWebModel;

public class UserWebApiClient extends GenericRestClient<UserWebApi> {

    public UserWebApiClient(String url) {
        super(url, UserWebApi.class);
    }

    public UserCrudWebModel getUserById(Long id) {
        return resource.getUserById(id);
    }

    public UserCrudWebModel updateUser(Long id, UserCrudWebModel model) {
        return resource.updateUser(id, model);
    }

    public void archivizeUser(Long id) {
        resource.archivizeUser(id);
    }

    public UserSearchResult queryUsers(UserSearchWebModel model) {
        return resource.queryUsers(model);
    }
}
