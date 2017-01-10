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
public class TimeWindowWebModel {
    private Long id;
    private String start;
    private String end;

}
