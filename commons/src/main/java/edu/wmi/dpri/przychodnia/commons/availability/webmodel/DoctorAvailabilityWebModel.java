package edu.wmi.dpri.przychodnia.commons.availability.webmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Created by lupus on 10.01.17.
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DoctorAvailabilityWebModel {
    private String weekday; //MONDAY, TUESDAY etc.
    private Long timeWindowStartId;
    private Long timeWindowEndId;
    private Long validFrom;
    private Long validTo;
    @NotNull
    private Long doctorId;
    private Long availabilityId;
}
