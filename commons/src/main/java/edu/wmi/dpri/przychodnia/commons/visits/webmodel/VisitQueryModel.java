package edu.wmi.dpri.przychodnia.commons.visits.webmodel;

import edu.wmi.dpri.przychodnia.commons.visits.enums.VisitStatusType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Created by lupus on 01.01.17.
 */
@Data
@NoArgsConstructor
public class VisitQueryModel {

    @NotNull
    private VisitStatusType status;
    @NotNull
    private String type;
    @NotNull
    private Long from;
    //not needed for some requests
    private Long doctorId;
    private Long patientId;

}
