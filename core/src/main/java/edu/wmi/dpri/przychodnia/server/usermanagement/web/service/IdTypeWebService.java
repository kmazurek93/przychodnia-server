package edu.wmi.dpri.przychodnia.server.usermanagement.web.service;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.IdTypeWebModel;
import edu.wmi.dpri.przychodnia.server.entity.IdType;
import edu.wmi.dpri.przychodnia.server.usermanagement.function.IdTypeToIdTypeWebModelFunction;
import edu.wmi.dpri.przychodnia.server.usermanagement.service.IdTypeService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

@Service
public class IdTypeWebService {
    @Inject
    private IdTypeService idTypeService;

    @Inject
    private IdTypeToIdTypeWebModelFunction function;

    public IdTypeWebModel getOne(Integer id) {
        IdType entity = idTypeService.getById(id);
        return function.apply(entity);
    }

    public IdTypeWebModel createOne(IdTypeWebModel input) {
        IdType entity = idTypeService.createIdType(input);
        return function.apply(entity);
    }

    public IdTypeWebModel updateIdType(IdTypeWebModel toUpdate) {
        IdType entityUpdated = idTypeService.updateIdType(toUpdate);
        return function.apply(entityUpdated);
    }

    public Response deleteOne(Integer id) {
        return idTypeService.deleteIdType(id);
    }
}
