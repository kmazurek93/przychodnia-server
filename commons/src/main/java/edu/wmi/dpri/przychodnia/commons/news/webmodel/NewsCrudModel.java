package edu.wmi.dpri.przychodnia.commons.news.webmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by lupus on 11.01.17.
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsCrudModel {
    private Long id;
    private String title;
    private String content;
}
