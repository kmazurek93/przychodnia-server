package edu.wmi.dpri.przychodnia.server.usermanagement.function;

import com.google.common.base.Function;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.UserDataWebModel;
import edu.wmi.dpri.przychodnia.server.entity.Role;
import edu.wmi.dpri.przychodnia.server.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static edu.wmi.dpri.przychodnia.commons.usermanagement.builder.UserDataWebModelBuilder.anUserDataWebModel;


@Component
public class UserToUserDataWebModelFunction implements Function<User, UserDataWebModel> {
    @Override
    public UserDataWebModel apply(User input) {
        return anUserDataWebModel().withEmailAddress(input.getEmailAddress()).withId(input.getId())
                .withPassword(input.getPassword()).withRoles(handleRoles(input.getRoles()))
                .withUsername(input.getLogin()).withActive(input.isActive()).build();
    }

    private List<String> handleRoles(List<Role> roles) {
        return roles.stream().map(Role::getName).collect(Collectors.toList());
    }
}
