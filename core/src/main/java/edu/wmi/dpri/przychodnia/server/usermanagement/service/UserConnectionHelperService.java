package edu.wmi.dpri.przychodnia.server.usermanagement.service;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.creation.UserLinkingUnlinkingWebModel;
import edu.wmi.dpri.przychodnia.server.entity.Person;
import edu.wmi.dpri.przychodnia.server.entity.User;
import org.springframework.stereotype.Service;

import javax.inject.Inject;


@Service
public class UserConnectionHelperService {
    @Inject
    private PersonService personService;
    @Inject
    private UserService userService;
    @Inject
    private UserLinkingService userLinkingService;

    public void connectUsers(UserLinkingUnlinkingWebModel model) {
        Person parent = personService.findOneAndInitialize(model.getParentPesel());
        Person child = personService.findOneAndInitialize(model.getChildPesel());
        User parentUser = parent.getUsers().get(0);
        User childUser = child.getUsers().get(0);
        userLinkingService.connectUsers(parentUser.getId(), childUser.getId());
    }

    public void unlinkUsers(UserLinkingUnlinkingWebModel model) {
        Person parent = personService.findOneAndInitialize(model.getParentPesel());
        Person child = personService.findOneAndInitialize(model.getChildPesel());
        User parentUser = parent.getUsers().get(0);
        User childUser = child.getUsers().get(0);
        userLinkingService.removeConnection(parentUser.getId(), childUser.getId());
    }

}
