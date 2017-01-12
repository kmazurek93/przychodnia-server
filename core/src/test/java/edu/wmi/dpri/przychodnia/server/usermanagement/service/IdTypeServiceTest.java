package edu.wmi.dpri.przychodnia.server.usermanagement.service;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.IdTypeWebModel;
import edu.wmi.dpri.przychodnia.server.entity.IdType;
import edu.wmi.dpri.przychodnia.server.repository.IdTypeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.refEq;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;


public class IdTypeServiceTest {

    public static final String NEW_NAME = "new name";
    public static final long MODIFIED_ID_TYPE_ID = 123;
    private static final Long ID = 5555L;
    @Mock
    private IdTypeRepository idTypeRepository;
    @InjectMocks
    private IdTypeService tested;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void getById() throws Exception {
        // given
        IdType idType = new IdType();
        when(idTypeRepository.findOne(eq(ID))).thenReturn(idType);
        // when
        IdType actual = tested.getById(ID);
        // then
        assertThat(actual).isEqualTo(idType);
    }

    @Test
    public void updateIdType() throws Exception {
        // given
        IdType oldIdType = new IdType();
        oldIdType.setId(MODIFIED_ID_TYPE_ID);
        oldIdType.setName("name");
        ArgumentCaptor<IdType> captor = ArgumentCaptor.forClass(IdType.class);
        IdType newIdType = new IdType();
        newIdType.setId(MODIFIED_ID_TYPE_ID);
        newIdType.setName(NEW_NAME);
        IdTypeWebModel toUpdate = new IdTypeWebModel();
        toUpdate.setId(MODIFIED_ID_TYPE_ID);
        toUpdate.setName(NEW_NAME);
        when(idTypeRepository.findOne(eq(MODIFIED_ID_TYPE_ID))).thenReturn(oldIdType);
        when(idTypeRepository.save(captor.capture())).thenReturn(newIdType);
        // when
        IdType actual = tested.updateIdType(toUpdate);
        // then
        IdType capturedValue = captor.getValue();
        assertThat(capturedValue.getName()).isEqualTo(NEW_NAME);
        assertThat(actual).isEqualTo(newIdType);
    }

    @Test
    public void createIdType() throws Exception {
        // given
        IdTypeWebModel idTypeWebModel = new IdTypeWebModel();
        IdType idType = new IdType();
        when(idTypeRepository.save(refEq(idType))).thenReturn(idType);
        // when
        IdType actual = tested.createIdType(idTypeWebModel);
        // then
        assertThat(actual).isEqualTo(idType);
    }

    @Test
    public void deleteIdType() throws Exception {
        // given
        IdType idType = new IdType();
        when(idTypeRepository.findOne(eq(ID))).thenReturn(idType);
        doNothing().when(idTypeRepository).delete(eq(idType));
        // when
        Response actual = tested.deleteIdType(ID);
        // then
        assertThat(actual.getStatus()).isEqualTo(204);
        verify(idTypeRepository, times(1)).delete(eq(idType));
    }

}