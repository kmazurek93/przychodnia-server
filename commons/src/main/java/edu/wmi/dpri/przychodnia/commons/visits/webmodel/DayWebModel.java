package edu.wmi.dpri.przychodnia.commons.visits.webmodel;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by lupus on 25.11.16.
 */
@Data
@NoArgsConstructor
public class DayWebModel {
    private Long dayStart;
    private Boolean available;

}
