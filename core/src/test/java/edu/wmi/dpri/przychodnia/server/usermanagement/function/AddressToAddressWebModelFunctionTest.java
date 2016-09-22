package edu.wmi.dpri.przychodnia.server.usermanagement.function;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.AddressWebModel;
import edu.wmi.dpri.przychodnia.server.entity.Address;
import org.junit.Test;

import static edu.wmi.dpri.przychodnia.server.usermanagement.providers.SampleDataProvider.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by lupus on 22.09.16.
 */
public class AddressToAddressWebModelFunctionTest {

    public static final long ADDRESS_ID = 124;
    private AddressToAddressWebModelFunction tested = new AddressToAddressWebModelFunction();


    @Test
    public void apply() throws Exception {
        //given
        Address address = getAddress();
        //when
        AddressWebModel actual = tested.apply(address);
        //then
        assertThat(actual.getId()).isEqualTo(ADDRESS_ID);
        assertThat(actual.getApartment()).isEqualTo(APARTMENT);
        assertThat(actual.getCity()).isEqualTo(CITY);
        assertThat(actual.getCountry()).isEqualTo(COUNTRY);
        assertThat(actual.getHouse()).isEqualTo(HOUSE);
        assertThat(actual.getPostCode()).isEqualTo(POST_CODE);
        assertThat(actual.getProvince()).isEqualTo(A_PROVINCE);
        assertThat(actual.getStreet()).isEqualTo(STREET);

    }

    private Address getAddress() {
        Address outcome = new Address();
        outcome.setApartment(APARTMENT);
        outcome.setCity(CITY);
        outcome.setCountry(COUNTRY);
        outcome.setHouse(HOUSE);
        outcome.setPostCode(POST_CODE);
        outcome.setProvince(A_PROVINCE);
        outcome.setStreet(STREET);
        outcome.setId(ADDRESS_ID);
        return outcome;
    }

}