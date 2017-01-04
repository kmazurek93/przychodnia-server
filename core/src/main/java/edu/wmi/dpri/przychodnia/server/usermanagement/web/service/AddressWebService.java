package edu.wmi.dpri.przychodnia.server.usermanagement.web.service;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.AddressWebModel;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.creation.AddressCreationWebModel;
import edu.wmi.dpri.przychodnia.commons.publics.webmodel.RegistrationInputWebModel;
import edu.wmi.dpri.przychodnia.server.entity.Address;
import edu.wmi.dpri.przychodnia.server.usermanagement.function.AddressCreationWebModelToAddressFunction;
import edu.wmi.dpri.przychodnia.server.usermanagement.function.AddressToAddressWebModelFunction;
import edu.wmi.dpri.przychodnia.server.usermanagement.service.AddressService;
import edu.wmi.dpri.przychodnia.server.usermanagement.state.UserRegisteringState;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

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
        return convertEntityToWebModel(address);
    }

    public AddressWebModel createAddress(AddressCreationWebModel addressCreationWebModel) {
        Address address = convertCreationModelToNewEntity(addressCreationWebModel);
        Address savedAddress = addressService.saveAddress(address);
        return convertEntityToWebModel(savedAddress);
    }

    public AddressWebModel update(AddressWebModel addressWebModel) {
        Address address = addressService.updateAddress(addressWebModel);
        return convertEntityToWebModel(address);
    }

    public Response deleteOne(Long id) {
        addressService.deleteOne(id);
        return Response.noContent().build();
    }

    public List<AddressWebModel> getAll() {
        List<Address> all = addressService.getAll();
        return all.stream().map(this::convertEntityToWebModel).collect(Collectors.toList());
    }

    private AddressWebModel convertEntityToWebModel(Address o) {
        return entityToWebModelFunction.apply(o);
    }

    public void handleAddingAddressesDuringRegistration(UserRegisteringState state) {
        RegistrationInputWebModel registrationInputWebModel = state.getRegistrationInputWebModel();
        AddressCreationWebModel address = registrationInputWebModel.getAddress();
        AddressCreationWebModel mailingAddress = registrationInputWebModel.getMailingAddress();
        Address savedAddress = addressService.saveAddress(convertCreationModelToNewEntity(address));
        state.setSavedAddress(savedAddress);
        if (mailingAddress != null) {
            Address savedMailingAddress = addressService.saveAddress(convertCreationModelToNewEntity
                    (mailingAddress));
            state.setSavedMailingAddress(savedMailingAddress);
        }
    }

    private Address convertCreationModelToNewEntity(AddressCreationWebModel address) {
        return creationWebModelToAddressFunction.apply(address);
    }
}
