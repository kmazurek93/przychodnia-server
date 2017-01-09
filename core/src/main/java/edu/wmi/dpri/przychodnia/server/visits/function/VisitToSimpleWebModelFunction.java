package edu.wmi.dpri.przychodnia.server.visits.function;

import com.google.common.base.Function;
import edu.wmi.dpri.przychodnia.commons.visits.webmodel.SimpleVisitWebModel;
import edu.wmi.dpri.przychodnia.server.entity.Person;
import edu.wmi.dpri.przychodnia.server.entity.Visit;
import edu.wmi.dpri.przychodnia.server.visits.utils.VisitUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lupus on 31.12.16.
 */
@Component
public class VisitToSimpleWebModelFunction implements Function<Visit, SimpleVisitWebModel> {

    @Inject
    private VisitUtils visitUtils;

    @Override
    public SimpleVisitWebModel apply(Visit input) {
        SimpleVisitWebModel outcome = new SimpleVisitWebModel();
        outcome.setDoctorId(input.getDoctor().getId());
        outcome.setDoctorName(createDoctorName(input));
        outcome.setStatus(input.getStatus());
        outcome.setPatientName(createPatientName(input));
        outcome.setPatientPesel(input.getPatient().getPerson().getPESEL());
        outcome.setVisitId(input.getId());
        visitUtils.fillDates(input, outcome);
        return outcome;
    }



    private String createPatientName(Visit input) {
        Person person = input.getPatient().getPerson();
        return StringUtils.join(person.getFirstName(), " ", person.getLastName());
    }

    private String createDoctorName(Visit input) {
        Person person = input.getDoctor().getEmployee().getPerson();
        return StringUtils.join(person.getFirstName(), " ", person.getLastName());
    }

    public List<SimpleVisitWebModel> convertAll(List<Visit> visits) {
        return visits.stream().map(this::apply).collect(Collectors.toList());
    }
}
