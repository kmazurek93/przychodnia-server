package edu.wmi.dpri.przychodnia.commons.visits.webmodel;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class SimpleAvailabilityWebModel {
    private Long visitId;
    private String timeStart;
    private String timeEnd;
    private String date;
    private String status;
    private String type;
    private Integer order;
}
