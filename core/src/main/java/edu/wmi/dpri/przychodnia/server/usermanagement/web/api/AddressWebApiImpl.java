package edu.wmi.dpri.przychodnia.server.usermanagement.web.api;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webapi.AddressWebApi;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.AddressWebModel;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.creation.AddressCreationWebModel;
import edu.wmi.dpri.przychodnia.server.usermanagement.web.service.AddressWebService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

/**
 * Created by lupus on 22.09.16.
 */
@Service
public class AddressWebApiImpl implements AddressWebApi {
    @Inject
    private AddressWebService addressWebService;

    @Override
    public AddressWebModel getAddressById(Long id) {
        return addressWebService.getAddressById(id);
    }

    @Override
    public AddressWebModel createAddress(AddressCreationWebModel addressCreationWebModel) {
        return addressWebService.createAddress(addressCreationWebModel);
    }

    @Override
    public AddressWebModel update(AddressWebModel addressWebModel) {
        return addressWebService.update(addressWebModel);
    }

    @Override
    public Response deleteOne(Long id) {
        return addressWebService.deleteOne(id);
    }
}
