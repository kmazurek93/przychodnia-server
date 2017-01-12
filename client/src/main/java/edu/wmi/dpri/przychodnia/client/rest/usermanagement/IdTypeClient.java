package edu.wmi.dpri.przychodnia.client.rest.usermanagement;

import edu.wmi.dpri.przychodnia.commons.rest.GenericRestClient;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webapi.IdTypeManagementWebApi;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.IdTypeWebModel;

import javax.ws.rs.core.Response;

public class IdTypeClient extends GenericRestClient<IdTypeManagementWebApi> {

    public IdTypeClient(String url) {
        super(url, IdTypeManagementWebApi.class);
    }

    public IdTypeWebModel getOne(Long id) {
        return resource.getOne(id);
    }

    public IdTypeWebModel createOne(IdTypeWebModel input) {
        return resource.createOne(input);
    }

    public IdTypeWebModel updateIdType(IdTypeWebModel toUpdate) {
        return resource.updateIdType(toUpdate);
    }

    public Response deleteOne(Long id) {
        return resource.deleteOne(id);
    }
}
