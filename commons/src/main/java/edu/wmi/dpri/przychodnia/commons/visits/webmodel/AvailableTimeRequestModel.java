package edu.wmi.dpri.przychodnia.commons.visits.webmodel;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Created by lupus on 25.11.16.
 */
@Data
@NoArgsConstructor
public class AvailableTimeRequestModel {
    @NotNull
    private Long doctorId;
    @NotNull
    private Long startDate;
    private Long endDate;
}
