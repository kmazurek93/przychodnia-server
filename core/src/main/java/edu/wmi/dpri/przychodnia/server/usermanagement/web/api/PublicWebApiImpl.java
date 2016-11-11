package edu.wmi.dpri.przychodnia.server.usermanagement.web.api;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webapi.PublicWebApi;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.IdTypeWebModel;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.RegistrationOutput;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.SexWebModel;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.creation.RegistrationInputWebModel;
import edu.wmi.dpri.przychodnia.server.usermanagement.web.service.IdTypeWebService;
import edu.wmi.dpri.przychodnia.server.usermanagement.web.service.RegistrationWebService;
import edu.wmi.dpri.przychodnia.server.usermanagement.web.service.SexWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lupus on 18.10.16.
 */
@Service
public class PublicWebApiImpl implements PublicWebApi {

    @Autowired
    private RegistrationWebService registrationWebService;
    @Autowired
    private IdTypeWebService idTypeWebService;
    @Autowired
    private SexWebService sexWebService;

    @Override
    public RegistrationOutput registerUser(RegistrationInputWebModel registrationInputWebModel) {
        return registrationWebService.registerUserAndReturnOutput(registrationInputWebModel);
    }

    @Override
    public List<SexWebModel> getAllSexes() {
        return sexWebService.getAllSexes();
    }

    @Override
    public List<IdTypeWebModel> getAllIdTypes() {
        return idTypeWebService.getAll();
    }
}
