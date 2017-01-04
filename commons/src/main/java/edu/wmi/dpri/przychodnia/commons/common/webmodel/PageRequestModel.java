package edu.wmi.dpri.przychodnia.commons.common.webmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by lupus on 04.01.17.
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageRequestModel {
    private Integer page;
    private Integer size;
}
