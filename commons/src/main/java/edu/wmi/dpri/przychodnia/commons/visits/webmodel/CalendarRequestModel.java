package edu.wmi.dpri.przychodnia.commons.visits.webmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CalendarRequestModel {
    @NotNull
    private Long doctorId;
    @NotNull
    private Long startDate;
    @NotNull
    private Long endDate;
}
