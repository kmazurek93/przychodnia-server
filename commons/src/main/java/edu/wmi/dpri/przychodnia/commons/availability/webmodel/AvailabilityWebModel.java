package edu.wmi.dpri.przychodnia.commons.availability.webmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by lupus on 10.01.17.
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AvailabilityWebModel {
    private Long id;
    private Integer weekday;
    private Long startTimeWindowId;
    private Long endTimeWindowId;
    private Long validFrom;
    private Long validTo;
}
