package edu.wmi.dpri.przychodnia.commons.visits.webmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static jersey.repackaged.com.google.common.collect.Lists.newArrayList;

/**
 * Created by lupus on 01.01.17.
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FullVisitWebModel extends SimpleVisitWebModel{
    private String doctorName;
    private String comment;
    private Long parentVisitId;
    private List<Long> childVisitsIds = newArrayList();
}
