package edu.wmi.dpri.przychodnia.commons.visits.webmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.wmi.dpri.przychodnia.commons.visits.enums.VisitStatusType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Created by lupus on 25.11.16.
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SimpleVisitWebModel {
    private Long visitId;
    @NotNull
    private Long doctorId;
    private String doctorName;
    @NotNull
    private String patientPesel;
    private String patientName;
    @NotNull
    private Long start;
    private Long end;
    private VisitStatusType status;
}
