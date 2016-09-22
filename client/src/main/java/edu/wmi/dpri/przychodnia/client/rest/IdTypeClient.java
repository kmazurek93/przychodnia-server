package edu.wmi.dpri.przychodnia.client.rest;

import edu.wmi.dpri.przychodnia.commons.rest.GenericRestClient;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webapi.IdTypeManagementWebApi;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.IdTypeWebModel;

import javax.ws.rs.core.Response;

/**
 * Created by lupus on 21.09.16.
 */
public class IdTypeClient extends GenericRestClient<IdTypeManagementWebApi> {

    public IdTypeClient(String url) {
        super(url, IdTypeManagementWebApi.class);
    }

    public IdTypeWebModel getOne(Integer id) {
        return resource.getOne(id);
    }

    public IdTypeWebModel createOne(IdTypeWebModel input) {
        return resource.createOne(input);
    }

    public IdTypeWebModel updateIdType(IdTypeWebModel toUpdate) {
        return resource.updateIdType(toUpdate);
    }

    public Response deleteOne(Integer id) {
        return resource.deleteOne(id);
    }
}
