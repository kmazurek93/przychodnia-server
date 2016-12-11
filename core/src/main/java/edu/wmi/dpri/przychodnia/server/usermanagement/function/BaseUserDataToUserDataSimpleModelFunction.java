package edu.wmi.dpri.przychodnia.server.usermanagement.function;

import com.google.common.base.Function;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.UserDataSimpleModel;
import edu.wmi.dpri.przychodnia.server.entity.views.BaseUserData;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static edu.wmi.dpri.przychodnia.commons.usermanagement.builder.UserDataSimpleModelBuilder.anUserDataSimpleModel;

/**
 * Created by lupus on 11.12.16.
 */
@Component
public class BaseUserDataToUserDataSimpleModelFunction implements Function<BaseUserData, UserDataSimpleModel> {

    @Override
    public UserDataSimpleModel apply(BaseUserData input) {
        return anUserDataSimpleModel()
                .withPesel(input.getPesel())
                .withName(input.getName())
                .withUserId(input.getUserId())
                .withEmail(input.getEmail())
                .withTelephone(input.getTelephone())
                .withAddress(input.getAddress())
                .withMailingAddress(input.getMailingAddress())
                .build();
    }

    public List<UserDataSimpleModel> convertAll(Collection<? extends BaseUserData> input) {
        return input.stream().map(this::apply).collect(Collectors.toList());
    }
}
