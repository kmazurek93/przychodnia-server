package edu.wmi.dpri.przychodnia.commons.visits.webmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.wmi.dpri.przychodnia.commons.visits.enums.VisitStatusType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class VisitStatusChangeModel {
    @NotNull
    private VisitStatusType status;
}
