package edu.wmi.dpri.przychodnia.server.usermanagement.function;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.creation.AddressCreationWebModel;
import edu.wmi.dpri.przychodnia.server.entity.Address;
import org.junit.Test;

import static edu.wmi.dpri.przychodnia.server.usermanagement.providers.SampleDataProvider.*;
import static org.assertj.core.api.Assertions.assertThat;


public class AddressCreationWebModelToAddressFunctionTest {


    private AddressCreationWebModelToAddressFunction tested = new
            AddressCreationWebModelToAddressFunction();

    @Test
    public void apply() throws Exception {
        // given
        AddressCreationWebModel addressCreationWebModel = getAddressCreationWebModel();
        // when
        Address actual = tested.apply(addressCreationWebModel);
        // then
        assertThat(actual.getApartment()).isEqualTo(APARTMENT);
        assertThat(actual.getCity()).isEqualTo(CITY);
        assertThat(actual.getCountry()).isEqualTo(COUNTRY);
        assertThat(actual.getHouse()).isEqualTo(HOUSE);
        assertThat(actual.getId()).isNull();
        assertThat(actual.getPostCode()).isEqualTo(POST_CODE);
        assertThat(actual.getProvince()).isEqualTo(A_PROVINCE);
        assertThat(actual.getStreet()).isEqualTo(STREET);
        assertThat(actual.getPersons()).isNullOrEmpty();
        assertThat(actual.getPersonsWithMailingAddress()).isNullOrEmpty();
    }

    private AddressCreationWebModel getAddressCreationWebModel() {
        AddressCreationWebModel outcome = new AddressCreationWebModel();
        outcome.setApartment(APARTMENT);
        outcome.setCity(CITY);
        outcome.setCountry(COUNTRY);
        outcome.setHouse(HOUSE);
        outcome.setPostCode(POST_CODE);
        outcome.setProvince(A_PROVINCE);
        outcome.setStreet(STREET);
        return outcome;
    }

}