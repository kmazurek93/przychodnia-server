package edu.wmi.dpri.przychodnia.commons.visits.webmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.wmi.dpri.przychodnia.commons.visits.enums.VisitStatusType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Created by lupus on 01.01.17.
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FullVisitWebModel {
    @NotNull
    private String patientPesel;
    @NotNull
    private Long doctorId;
    @NotNull
    private Long date;
    private Long timeWindowId;
    @NotNull
    private VisitStatusType status;
    private Long associatedVisitId;
    private String comment;
}
