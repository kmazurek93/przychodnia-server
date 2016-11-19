package edu.wmi.dpri.przychodnia.server.usermanagement.service;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.UserSearchWebModel;
import edu.wmi.dpri.przychodnia.server.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by lupus on 19.11.16.
 */
@Component
public class UserSearchService {

    public List<User> queryAll(UserSearchWebModel userSearchWebModel) {
        fillPercentSigns(userSearchWebModel);
        //TODO
        return null;
    }

    private void fillPercentSigns(UserSearchWebModel userSearchWebModel) {
        userSearchWebModel.setTelephone(StringUtils.join("%", userSearchWebModel.getTelephone(), "%"));
        userSearchWebModel.setAddress(StringUtils.join("%", userSearchWebModel.getAddress(), "%"));
        userSearchWebModel.setMail(StringUtils.join("%", userSearchWebModel.getMail(), "%"));
        userSearchWebModel.setName(StringUtils.join("%", userSearchWebModel.getName(), "%"));
        userSearchWebModel.setRole(StringUtils.join("%", userSearchWebModel.getRole(), "%"));
    }
}
