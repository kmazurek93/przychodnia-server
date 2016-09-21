package edu.wmi.dpri.przychodnia.server.usermanagement.web.api;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webapi.IdTypeManagementWebApi;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.IdTypeWebModel;
import edu.wmi.dpri.przychodnia.server.usermanagement.web.service.IdTypeWebService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

/**
 * Created by kmazu on 07.09.2016.
 */
@Service

public class IdTypeManagementWebApiImpl implements IdTypeManagementWebApi {

	@Inject
	private IdTypeWebService idTypeWebService;

	@Override
	public IdTypeWebModel getOne(Integer id) {
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
	public Response deleteOne(Integer id) {
		return idTypeWebService.deleteOne(id);
	}

}
