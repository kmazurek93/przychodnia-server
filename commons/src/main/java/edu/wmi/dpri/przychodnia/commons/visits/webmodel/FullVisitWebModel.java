package edu.wmi.dpri.przychodnia.commons.visits.webmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

import static jersey.repackaged.com.google.common.collect.Lists.newArrayList;


@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FullVisitWebModel extends SimpleVisitWebModel {
    private String doctorName;
    private String comment;
    private Long parentVisitId;
    private List<Long> childVisitsIds = newArrayList();
}
