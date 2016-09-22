package edu.wmi.dpri.przychodnia.server.usermanagement.function;

import com.google.common.base.Function;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.creation.AddressCreationWebModel;
import edu.wmi.dpri.przychodnia.server.entity.Address;
import org.springframework.stereotype.Component;

/**
 * Created by lupus on 22.09.16.
 */
@Component
public class AddressCreationWebModelToAddressFunction
        implements Function<AddressCreationWebModel, Address> {

    @Override
    public Address apply(AddressCreationWebModel input) {
        Address outcome = new Address();
        outcome.setStreet(input.getStreet());
        outcome.setPostCode(input.getPostCode());
        outcome.setProvince(input.getProvince());
        outcome.setCity(input.getCity());
        outcome.setHouse(input.getHouse());
        outcome.setApartment(input.getApartment());
        outcome.setCountry(input.getCountry());
        return outcome;
    }
}
