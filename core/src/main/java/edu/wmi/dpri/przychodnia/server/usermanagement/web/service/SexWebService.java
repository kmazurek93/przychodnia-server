package edu.wmi.dpri.przychodnia.server.usermanagement.web.service;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.SexWebModel;
import edu.wmi.dpri.przychodnia.server.usermanagement.function.SexToWebModelFunction;
import edu.wmi.dpri.przychodnia.server.usermanagement.service.SexService;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;


@Component
public class SexWebService {

    @Inject
    private SexService sexService;
    @Inject
    private SexToWebModelFunction function;

    public List<SexWebModel> getAllSexes() {
        return function.applyToList(sexService.getAll());
    }
}
