package edu.wmi.dpri.przychodnia.client.rest.publics;

import edu.wmi.dpri.przychodnia.commons.common.webmodel.NewsWebModel;
import edu.wmi.dpri.przychodnia.commons.common.webmodel.PageRequestModel;
import edu.wmi.dpri.przychodnia.commons.common.webmodel.SimplePage;
import edu.wmi.dpri.przychodnia.commons.publics.webapi.PublicWebApi;
import edu.wmi.dpri.przychodnia.commons.publics.webmodel.RegistrationInputWebModel;
import edu.wmi.dpri.przychodnia.commons.rest.GenericRestClient;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.IdTypeWebModel;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.RegistrationOutput;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.SexWebModel;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public class PublicApiClient extends GenericRestClient<PublicWebApi> {
    public PublicApiClient(String url) {
        super(url, PublicWebApi.class);
    }

    public RegistrationOutput registerUser(@NotNull @Valid RegistrationInputWebModel registrationInputWebModel) {
        return resource.registerUser(registrationInputWebModel);
    }

    public List<SexWebModel> getAllSexes() {
        return resource.getAllSexes();
    }

    public List<IdTypeWebModel> getAllIdTypes() {
        return resource.getAllIdTypes();
    }

    public SimplePage<NewsWebModel> getNewsPage(PageRequestModel p) {
        return resource.getNewsPage(p);
    }
}
