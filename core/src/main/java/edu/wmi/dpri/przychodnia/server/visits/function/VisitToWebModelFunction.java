package edu.wmi.dpri.przychodnia.server.visits.function;

import com.google.common.base.Function;
import edu.wmi.dpri.przychodnia.commons.visits.webmodel.VisitWebModel;
import edu.wmi.dpri.przychodnia.server.entity.Visit;
import org.springframework.stereotype.Component;

/**
 * Created by lupus on 31.12.16.
 */
@Component
public class VisitToWebModelFunction implements Function<Visit, VisitWebModel> {


    @Override
    public VisitWebModel apply(Visit input) {
        //todo if needed
        return null;
    }
}
