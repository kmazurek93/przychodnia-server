package edu.wmi.dpri.przychodnia.server.usermanagement.web.api;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webapi.IdTypeManagementWebApi;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.IdTypeWebModel;
import edu.wmi.dpri.przychodnia.server.usermanagement.web.service.IdTypeWebService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;


@Service
public class IdTypeManagementWebApiImpl implements IdTypeManagementWebApi {

    @Inject
    private IdTypeWebService idTypeWebService;

    @Override
    public IdTypeWebModel getOne(Long id) {
        return idTypeWebService.getOne(id);
    }

    @Override
    public IdTypeWebModel createOne(IdTypeWebModel input) {
        return idTypeWebService.createOne(input);
    }

    @Override
    public IdTypeWebModel updateIdType(IdTypeWebModel toUpdate) {
        return idTypeWebService.updateIdType(toUpdate);
    }

    @Override
    public Response deleteOne(Long id) {
        return idTypeWebService.deleteOne(id);
    }

    @Override
    public List<IdTypeWebModel> getAll() {
        return idTypeWebService.getAll();
    }

}
