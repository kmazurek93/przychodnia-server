package edu.wmi.dpri.przychodnia.server.visits.function;

import com.google.common.base.Function;
import edu.wmi.dpri.przychodnia.commons.visits.webmodel.FullVisitWebModel;
import edu.wmi.dpri.przychodnia.server.entity.Person;
import edu.wmi.dpri.przychodnia.server.entity.Visit;
import edu.wmi.dpri.przychodnia.server.visits.utils.VisitUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class VisitToFullDetailsModelFunction implements Function<Visit, FullVisitWebModel> {

    @Inject
    private VisitUtils visitUtils;

    @Override
    public FullVisitWebModel apply(Visit input) {
        FullVisitWebModel outcome = new FullVisitWebModel();
        outcome.setPatientPesel(input.getPatient().getPerson().getPESEL());
        outcome.setPatientName(createName(input.getPatient().getPerson()));
        outcome.setDoctorId(input.getDoctor().getId());
        outcome.setDoctorName(createName(input.getDoctor().getEmployee().getPerson()));
        visitUtils.fillDates(input, outcome);
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
        List<Visit> childVisits = input.getVisitsAssociatedWith();
        return childVisits == null ? null : childVisits
                                                .stream()
                                                .map(Visit::getId)
                                                .collect(Collectors.toList());
    }

    private String createName(Person person) {
        return StringUtils.join(person.getFirstName(), " ", person.getLastName());
    }

    public List<FullVisitWebModel> convertAll(List<Visit> content) {
        return content.stream().map(this::apply).collect(Collectors.toList());
    }
}
