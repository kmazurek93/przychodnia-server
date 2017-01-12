package edu.wmi.dpri.przychodnia.commons.visits.webmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class VisitDateChangeModel {
    @NotNull
    private Long visitId;
    @NotNull
    private Long newStartDate;
}
