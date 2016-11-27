package edu.wmi.dpri.przychodnia.server.usermanagement.web.api;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.IdTypeWebModel;
import edu.wmi.dpri.przychodnia.server.usermanagement.web.service.IdTypeWebService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by kmazu on 07.09.2016.
 */
public class IdTypeManagementWebApiImplTest {

	private static final Long ID_TYPE_ID = 2341L;
	@Mock
	private IdTypeWebService idTypeWebService;
	@InjectMocks
	private IdTypeManagementWebApiImpl tested;

	@Before
	public void setUp() throws Exception {
		initMocks(this);
	}

	@Test
	public void getOne() throws Exception {
		// given
		IdTypeWebModel idTypeWebModel = new IdTypeWebModel();
		when(idTypeWebService.getOne(eq(ID_TYPE_ID))).thenReturn(idTypeWebModel);
		// when
		IdTypeWebModel actual = tested.getOne(ID_TYPE_ID);
		// then
		assertThat(actual).isEqualTo(idTypeWebModel);
	}

	@Test
	public void createOne() throws Exception {
		// given
		IdTypeWebModel idTypeWebModel = new IdTypeWebModel();
		IdTypeWebModel webModelToReturn = new IdTypeWebModel();
		when(idTypeWebService.createOne(eq(idTypeWebModel))).thenReturn(webModelToReturn);
		// when
		IdTypeWebModel actual = tested.createOne(idTypeWebModel);
		// then
		assertThat(actual).isEqualTo(webModelToReturn);
	}

	@Test
	public void updateIdType() throws Exception {
		// given
		IdTypeWebModel idTypeWebModel = new IdTypeWebModel();
		IdTypeWebModel webModelToReturn = new IdTypeWebModel();
		when(idTypeWebService.updateIdType(eq(idTypeWebModel))).thenReturn(webModelToReturn);
		// when
		IdTypeWebModel actual = tested.updateIdType(idTypeWebModel);
		// then
		assertThat(actual).isEqualTo(webModelToReturn);
	}

	@Test
	public void deleteOne() throws Exception {
		// given
		Response noContentResponse = Response.noContent().build();
		when(idTypeWebService.deleteOne(eq(ID_TYPE_ID))).thenReturn(noContentResponse);
		// when
		Response actual = tested.deleteOne(ID_TYPE_ID);
		// then
		assertThat(actual).isEqualTo(noContentResponse);
	}

}