package edu.wmi.dpri.przychodnia.server.usermanagement.web.api;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webapi.RegistrationWebApi;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.RegistrationOutput;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.creation.RegistrationInputWebModel;
import edu.wmi.dpri.przychodnia.server.usermanagement.web.service.RegistrationWebService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by lupus on 18.10.16.
 */
@Service
public class RegistrationWebApiImpl implements RegistrationWebApi {

    @Inject
    private RegistrationWebService registrationWebService;

    @Override
    public RegistrationOutput registerUser(RegistrationInputWebModel registrationInputWebModel) {
        return registrationWebService.registerUserAndReturnOutput(registrationInputWebModel);
    }
}
