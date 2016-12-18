package edu.wmi.dpri.przychodnia.commons.visits.webmodel;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by lupus on 25.11.16.
 */
@Data
@NoArgsConstructor
public class VisitWebModel {
    private Long visitId;
    private Long doctorId;
    private String patientPesel;
    private Boolean available;
    private String patientName;
    private Long start;
    private Long end;


}
