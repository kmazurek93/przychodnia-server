package edu.wmi.dpri.przychodnia.server.visits.web.service;

import edu.wmi.dpri.przychodnia.server.visits.function.VisitToWebModelFunction;
import edu.wmi.dpri.przychodnia.server.visits.service.VisitService;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * Created by lupus on 31.12.16.
 */
@Component
public class VisitsWebService {
    @Inject
    private VisitService visitService;

    @Inject
    private VisitToWebModelFunction function;


    //TODO implement visit: create, accept/decline, remove, edit, doctor's edit
    //TODO get Patient visits
    //TODO get doctor visits
}
