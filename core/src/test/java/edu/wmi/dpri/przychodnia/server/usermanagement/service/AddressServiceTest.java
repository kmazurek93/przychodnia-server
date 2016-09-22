package edu.wmi.dpri.przychodnia.server.usermanagement.service;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.AddressWebModel;
import edu.wmi.dpri.przychodnia.server.entity.Address;
import edu.wmi.dpri.przychodnia.server.repository.AddressRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by lupus on 22.09.16.
 */
public class AddressServiceTest {

    public static final String NEWCOUNTRY = "NEWCOUNTRY";
    public static final String HOUSE = "33A";
    public static final long ADDRESS_ID = 123L;
    @Mock
    private AddressRepository repository;
    @InjectMocks
    private AddressService tested;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void saveAddress() throws Exception {
        // given
        Address address = new Address();
        Address saved = new Address();
        when(repository.save(eq(address))).thenReturn(saved);
        // when
        Address actual = tested.saveAddress(address);
        // then
        assertThat(actual).isEqualTo(saved);
    }

    @Test
    public void updateAddress() throws Exception {
        // given
        AddressWebModel addressWebModel = getSampleWebModel();
        Address address = getSampleUnupdatedAddress();
        Address updated = getSampleUpdatedAddress();
        ArgumentCaptor<Address> captor = ArgumentCaptor.forClass(Address.class);
        when(repository.findOne(eq(ADDRESS_ID))).thenReturn(address);
        when(repository.save(captor.capture())).thenReturn(updated);
        // when
        Address actual = tested.updateAddress(addressWebModel);
        // then
        assertThat(actual).isEqualTo(updated);
        Address capturedAddress = captor.getValue();
        assertThat(capturedAddress.getHouse()).isEqualTo(HOUSE);
        assertThat(capturedAddress.getCountry()).isEqualTo(NEWCOUNTRY);
        assertThat(capturedAddress.getId()).isEqualTo(ADDRESS_ID);
    }

    private Address getSampleUpdatedAddress() {
        Address address = getSampleUnupdatedAddress();
        address.setCountry(NEWCOUNTRY);
        address.setHouse(HOUSE);
        address.setId(ADDRESS_ID);
        return address;
    }

    private AddressWebModel getSampleWebModel() {
        AddressWebModel addressWebModel = new AddressWebModel();
        addressWebModel.setId(ADDRESS_ID);
        addressWebModel.setCountry(NEWCOUNTRY);
        addressWebModel.setHouse(HOUSE);
        return addressWebModel;
    }

    private Address getSampleUnupdatedAddress() {
        Address address = new Address();
        address.setId(ADDRESS_ID);
        address.setCountry("POLAND");
        return address;
    }

    @Test
    public void findOne() throws Exception {
        // given
        Address address = new Address();
        when(repository.findOne(eq(ADDRESS_ID))).thenReturn(address);
        // when
        Address actual = tested.findOne(ADDRESS_ID);
        // then
        assertThat(actual).isEqualTo(address);
    }

    @Test
    public void deleteOne() throws Exception {
        // when
        tested.deleteOne(ADDRESS_ID);
        // then
        verify(repository, times(1)).delete(eq(ADDRESS_ID));
    }


}