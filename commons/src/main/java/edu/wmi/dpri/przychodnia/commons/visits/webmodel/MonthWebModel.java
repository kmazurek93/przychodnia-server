package edu.wmi.dpri.przychodnia.commons.visits.webmodel;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class MonthWebModel {
    private Long monthStart;
    private Boolean available;

}
