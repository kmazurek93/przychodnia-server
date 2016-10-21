package edu.wmi.dpri.przychodnia.server.usermanagement.function;

import com.google.common.base.Function;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.AddressWebModel;
import edu.wmi.dpri.przychodnia.server.entity.Address;
import org.springframework.stereotype.Component;

/**
 * Created by lupus on 22.09.16.
 */
@Component
public class AddressToAddressWebModelFunction implements Function<Address, AddressWebModel> {

    @Override
    public AddressWebModel apply(Address input) {
        if (input == null) {
            return null;
        }
        AddressWebModel outcome = new AddressWebModel();
        outcome.setStreet(input.getStreet());
        outcome.setPostCode(input.getPostCode());
        outcome.setProvince(input.getProvince());
        outcome.setCity(input.getCity());
        outcome.setHouse(input.getHouse());
        outcome.setApartment(input.getApartment());
        outcome.setCountry(input.getCountry());
        outcome.setId(input.getId());
        return outcome;
    }
}
