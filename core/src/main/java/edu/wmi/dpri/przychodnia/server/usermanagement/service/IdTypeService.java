package edu.wmi.dpri.przychodnia.server.usermanagement.service;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.IdTypeWebModel;
import edu.wmi.dpri.przychodnia.server.entity.IdType;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.ExceptionCause;
import edu.wmi.dpri.przychodnia.server.repository.IdTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static edu.wmi.dpri.przychodnia.server.exceptionmanagement.NotFoundExceptionThrower.throwExceptionIfNull;


/**
 * Created by lupus on 31.07.16.
 */
@Service
public class IdTypeService {

    @Inject
    private IdTypeRepository idTypeRepository;

    @Transactional(readOnly = true)
    public IdType getById(Long id) {
        IdType foundEntity = idTypeRepository.findOne(id);
        throwExceptionIfNull(id, foundEntity, ExceptionCause.RETRIEVAL,
                IdType.class);
        return foundEntity;
    }

    @Transactional
    public IdType updateIdType(IdTypeWebModel idTypeWebModel) {
        IdType foundEntity = idTypeRepository.findOne(idTypeWebModel.getId());
        throwExceptionIfNull(idTypeWebModel.getId(), foundEntity, ExceptionCause.MODIFICATION,
                IdType.class);
        foundEntity.setName(idTypeWebModel.getName());
        return idTypeRepository.save(foundEntity);
    }

    @Transactional
    public IdType createIdType(IdTypeWebModel idTypeWebModel) {
        IdType idType = new IdType();
        idType.setName(idTypeWebModel.getName());
        return idTypeRepository.save(idType);
    }

    @Transactional
    public Response deleteIdType(Long id) {
        IdType foundEntity = idTypeRepository.findOne(id);
        throwExceptionIfNull(id, foundEntity, ExceptionCause.DELETION,
                IdType.class);
        idTypeRepository.delete(foundEntity);
        return Response.noContent().build();
    }

    @Transactional(readOnly = true)
    public List<IdType> getAll() {
        return newArrayList(idTypeRepository.findAll());
    }
}
