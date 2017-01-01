package edu.wmi.dpri.przychodnia.server.visits.function;

import edu.wmi.dpri.przychodnia.commons.visits.webmodel.FullVisitWebModel;
import edu.wmi.dpri.przychodnia.server.entity.Person;
import edu.wmi.dpri.przychodnia.server.entity.Visit;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by lupus on 01.01.17.
 */
@Component
public class VisitToFullDetailsModelFunction implements Function<Visit, FullVisitWebModel> {

    @Inject
    private VisitToSimpleWebModelFunction function;

    @Override
    public FullVisitWebModel apply(Visit input) {
        FullVisitWebModel outcome = new FullVisitWebModel();
        outcome.setPatientPesel(input.getPatient().getPerson().getPESEL());
        outcome.setPatientName(createName(input.getPatient().getPerson()));
        outcome.setDoctorId(input.getDoctor().getId());
        outcome.setDoctorName(createName(input.getDoctor().getEmployee().getPerson()));
        function.fillDates(input, outcome);
        outcome.setStatus(input.getStatus());
        outcome.setComment(input.getComment());
        outcome.setParentVisitId(getAssociatedVisitIdOrNull(input));
        outcome.setChildVisitsIds(createChildVisitsId(input));
        outcome.setVisitId(input.getId());
        return outcome;
    }

    private Long getAssociatedVisitIdOrNull(Visit input) {
        Visit associatedVisit = input.getAssociatedVisit();
        return associatedVisit != null ? associatedVisit.getId() : null;
    }

    private List<Long> createChildVisitsId(Visit input) {
        return input.getVisitsAssociatedWith()
                .stream()
                .map(Visit::getId)
                .collect(Collectors.toList());
    }

    private String createName(Person person) {
        return StringUtils.join(person.getFirstName(), " ", person.getLastName());
    }
}
