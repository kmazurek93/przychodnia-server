package edu.wmi.dpri.przychodnia.server.usermanagement.web.api;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webapi.UserWebApi;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.UserCrudWebModel;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.UserSearchResult;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.UserSearchWebModel;
import edu.wmi.dpri.przychodnia.server.usermanagement.web.service.UserWebService;

import javax.inject.Inject;

/**
 * Created by lupus on 14.11.16.
 */
public class UserWebApiImpl implements UserWebApi {
    @Inject
    private UserWebService userWebService;

    @Override
    public UserCrudWebModel getUserById(Long id) {
        return userWebService.getUserCompleteData(id);
    }

    @Override
    public UserCrudWebModel updateUser(Integer id, UserCrudWebModel model) {
        //TODO
        return null;
    }

    @Override
    public void archivizeUser(Long id) {
        userWebService.archivizeUser(id);
    }

    @Override
    public UserSearchResult queryUsers(UserSearchWebModel model) {

        return userWebService.queryUsers(model);
    }

}
