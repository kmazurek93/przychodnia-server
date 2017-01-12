package edu.wmi.dpri.przychodnia.server.usermanagement.function;

import com.google.common.base.Function;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.AddressWebModel;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.PersonalDataWebModel;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.UserCrudWebModel;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.UserDataWebModel;
import edu.wmi.dpri.przychodnia.server.entity.User;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

import static edu.wmi.dpri.przychodnia.commons.usermanagement.builder.UserCrudWebModelBuilder.anUserCrudWebModel;


@Component
public class UserToUserCrudWebModelFunction implements Function<User, UserCrudWebModel> {
    @Inject
    private AddressToAddressWebModelFunction addressWebModelFunction;
    @Inject
    private PersonToPersonalDataWebModelFunction personFunction;
    @Inject
    private UserToUserDataWebModelFunction userFunction;


    @Override
    public UserCrudWebModel apply(User input) {
        AddressWebModel address = addressWebModelFunction.apply(input.getPerson().getAddress());
        AddressWebModel mailingAddress = null;
        if (input.getPerson().getMailingAddress() != null) {
            mailingAddress = addressWebModelFunction.apply(input.getPerson().getMailingAddress());
        }
        PersonalDataWebModel personalDataWebModel = personFunction.apply(input.getPerson());
        UserDataWebModel userDataWebModel = userFunction.apply(input);
        return anUserCrudWebModel().withAddress(address).withMailingAddress(mailingAddress)
                .withPersonalData(personalDataWebModel).withUserData(userDataWebModel).build();
    }
}
