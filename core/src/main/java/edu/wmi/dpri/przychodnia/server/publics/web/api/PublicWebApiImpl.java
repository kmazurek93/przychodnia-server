package edu.wmi.dpri.przychodnia.server.publics.web.api;

import edu.wmi.dpri.przychodnia.commons.common.webmodel.NewsWebModel;
import edu.wmi.dpri.przychodnia.commons.common.webmodel.PageRequestModel;
import edu.wmi.dpri.przychodnia.commons.common.webmodel.SimplePage;
import edu.wmi.dpri.przychodnia.commons.publics.webapi.PublicWebApi;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.IdTypeWebModel;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.RegistrationOutput;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.SexWebModel;
import edu.wmi.dpri.przychodnia.commons.publics.webmodel.RegistrationInputWebModel;
import edu.wmi.dpri.przychodnia.server.publics.web.service.PublicNewsWebService;
import edu.wmi.dpri.przychodnia.server.usermanagement.web.service.IdTypeWebService;
import edu.wmi.dpri.przychodnia.server.usermanagement.web.service.RegistrationWebService;
import edu.wmi.dpri.przychodnia.server.usermanagement.web.service.SexWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PublicWebApiImpl implements PublicWebApi {

    @Autowired
    private RegistrationWebService registrationWebService;
    @Autowired
    private IdTypeWebService idTypeWebService;
    @Autowired
    private SexWebService sexWebService;
    @Autowired
    private PublicNewsWebService publicNewsWebService;

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

    @Override
    public SimplePage<NewsWebModel> getNewsPage(PageRequestModel p) {
        return publicNewsWebService.getNewsPage(p);
    }
}
