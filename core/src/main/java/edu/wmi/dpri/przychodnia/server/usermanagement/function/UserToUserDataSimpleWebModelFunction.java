package edu.wmi.dpri.przychodnia.server.usermanagement.function;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.UserDataSimpleWebModel;
import edu.wmi.dpri.przychodnia.server.entity.Role;
import edu.wmi.dpri.przychodnia.server.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;


/**
 * Created by lupus on 31.07.16.
 */
@Component
public class UserToUserDataSimpleWebModelFunction implements Function<User, UserDataSimpleWebModel> {

    private static final String SPACE = " ";

    @Override
    public UserDataSimpleWebModel apply(User user) {
        UserDataSimpleWebModel userDataSimpleModel = new UserDataSimpleWebModel();
        userDataSimpleModel.setFirstName(user.getPerson().getFirstName());
        userDataSimpleModel.setLastName(user.getPerson().getLastName());
        userDataSimpleModel.setEmailAddress(user.getEmailAddress());
        for (User childUser : user.getChildUsers()) {
            String firstName = childUser.getPerson().getFirstName();
            String lastName = childUser.getPerson().getLastName();
            String childName = StringUtils.join(firstName, SPACE, lastName);
            userDataSimpleModel.getChildAccountNames().add(childName);
        }
        for (User parentUser : user.getParentUsers()) {
            String firstName = parentUser.getPerson().getFirstName();
            String lastName = parentUser.getPerson().getLastName();
            String parentName = StringUtils.join(firstName, SPACE, lastName);
            userDataSimpleModel.getParentAccountNames().add(parentName);
        }
        List<String> userRoles = newArrayList();
        userRoles.addAll(user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toList()));
        userDataSimpleModel.setUserRoles(userRoles);
        userDataSimpleModel.setPESEL(user.getPerson().getPESEL());
        userDataSimpleModel.setUserId(user.getId());
        return userDataSimpleModel;
    }
}
