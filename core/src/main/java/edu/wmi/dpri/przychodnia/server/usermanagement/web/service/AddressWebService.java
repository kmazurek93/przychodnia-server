package edu.wmi.dpri.przychodnia.server.usermanagement.web.service;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.AddressWebModel;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.creation.AddressCreationWebModel;
import edu.wmi.dpri.przychodnia.server.entity.Address;
import edu.wmi.dpri.przychodnia.server.usermanagement.function.AddressCreationWebModelToAddressFunction;
import edu.wmi.dpri.przychodnia.server.usermanagement.function.AddressToAddressWebModelFunction;
import edu.wmi.dpri.przychodnia.server.usermanagement.service.AddressService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

/**
 * Created by lupus on 22.09.16.
 */
@Service
public class AddressWebService {
    @Inject
    private AddressService addressService;
    @Inject
    private AddressToAddressWebModelFunction entityToWebModelFunction;
    @Inject
    private AddressCreationWebModelToAddressFunction creationWebModelToAddressFunction;

    public AddressWebModel getAddressById(Long id) {
        Address address = addressService.findOne(id);
        return entityToWebModelFunction.apply(address);
    }

    public AddressWebModel createAddress(AddressCreationWebModel addressCreationWebModel) {
        Address address = creationWebModelToAddressFunction.apply(addressCreationWebModel);
        Address savedAddress = addressService.saveAddress(address);
        return entityToWebModelFunction.apply(savedAddress);
    }

    public AddressWebModel update(AddressWebModel addressWebModel) {
        Address address = addressService.updateAddress(addressWebModel);
        return entityToWebModelFunction.apply(address);
    }

    public Response deleteOne(Long id) {
        addressService.deleteOne(id);
        return Response.noContent().build();
    }
}
