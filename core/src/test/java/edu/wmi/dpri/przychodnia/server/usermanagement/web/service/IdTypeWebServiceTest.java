package edu.wmi.dpri.przychodnia.server.usermanagement.web.service;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.IdTypeWebModel;
import edu.wmi.dpri.przychodnia.server.entity.IdType;
import edu.wmi.dpri.przychodnia.server.usermanagement.function.IdTypeToIdTypeWebModelFunction;
import edu.wmi.dpri.przychodnia.server.usermanagement.service.IdTypeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by lupus on 11.09.16.
 */
public class IdTypeWebServiceTest {

    private static final Integer ID = 650;
    @Mock
    private IdTypeService idTypeService;
    @Mock
    private IdTypeToIdTypeWebModelFunction function;
    @InjectMocks
    private IdTypeWebService tested;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void getOne() throws Exception {
        //given
        IdType idType = new IdType();
        when(idTypeService.getById(eq(ID))).thenReturn(idType);
        IdTypeWebModel model = new IdTypeWebModel();
        when(function.apply(eq(idType))).thenReturn(model);
        // when
        IdTypeWebModel actual = tested.getOne(ID);
        // then
        assertThat(actual).isEqualTo(model);

    }

    @Test
    public void createOne() throws Exception {
        // given
        IdTypeWebModel idTypeWebModel = new IdTypeWebModel();
        IdType idType = new IdType();
        when(idTypeService.createIdType(eq(idTypeWebModel))).thenReturn(idType);
        when(function.apply(idType)).thenReturn(idTypeWebModel);
        //when
        IdTypeWebModel actual = tested.createOne(idTypeWebModel);
        // then
        assertThat(actual).isEqualTo(idTypeWebModel);
    }

    @Test
    public void updateIdType() throws Exception {
        // given
        IdTypeWebModel idTypeWebModel = new IdTypeWebModel();
        IdType idType = new IdType();
        when(idTypeService.updateIdType(eq(idTypeWebModel))).thenReturn(idType);
        when(function.apply(eq(idType))).thenReturn(idTypeWebModel);
        //when
        IdTypeWebModel actual = tested.updateIdType(idTypeWebModel);
        // then
        assertThat(actual).isEqualTo(idTypeWebModel);
    }

    @Test
    public void deleteOne() throws Exception {
        // given ID
        Response response = mock(Response.class);
        when(idTypeService.deleteIdType(eq(ID))).thenReturn(response);
        // when
        Response actual = tested.deleteOne(ID);
        // then
        assertThat(actual).isEqualTo(response);
    }

}