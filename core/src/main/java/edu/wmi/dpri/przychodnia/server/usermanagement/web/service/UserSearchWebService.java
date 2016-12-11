package edu.wmi.dpri.przychodnia.server.usermanagement.web.service;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.UserSearchWebModel;
import edu.wmi.dpri.przychodnia.server.entity.views.BaseUserData;
import edu.wmi.dpri.przychodnia.server.usermanagement.service.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

import static org.apache.commons.lang3.StringUtils.join;

/**
 * Created by lupus on 19.11.16.
 */
@Component
public class UserSearchWebService {

    @Inject
    private SearchService searchService;

    public Page<? extends BaseUserData> queryAll(UserSearchWebModel model) {
        fillPercentSigns(model);
        return searchService.queryOnAllUsers(model);
    }

    public Page<? extends BaseUserData> queryPatients(UserSearchWebModel model) {
        fillPercentSigns(model);
        return searchService.queryOnPatients(model);
    }

    public Page<? extends BaseUserData> queryStaff(UserSearchWebModel model) {
        fillPercentSigns(model);
        return searchService.queryOnEmployees(model);
    }

    private void fillPercentSigns(UserSearchWebModel model) {
        model.setTelephone(join("%", model.getTelephone(), "%"));
        model.setAddress(join("%", model.getAddress(), "%"));
        model.setMail(join("%", model.getMail(), "%"));
        model.setName(join("%", model.getName(), "%"));
        model.setPesel(join("%", model.getPesel(), "%"));
    }


}
