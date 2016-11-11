package edu.wmi.dpri.przychodnia.server.usermanagement.function;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.IdTypeWebModel;
import edu.wmi.dpri.przychodnia.server.entity.IdType;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Component;

import static edu.wmi.dpri.przychodnia.commons.usermanagement.builder.IdTypeWebModelBuilder.anIdTypeWebModel;

/**
 * Created by kmazu on 24.08.2016.
 */
@Component
public class IdTypeToWebModelFunction implements Function<IdType, IdTypeWebModel> {
    @Override
    public IdTypeWebModel apply(IdType idType) {
        return anIdTypeWebModel().withId(idType.getId()).withName(idType.getName()).build();
    }
}
