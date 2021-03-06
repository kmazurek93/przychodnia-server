package edu.wmi.dpri.przychodnia.commons.common.webmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsWebModel {
    private Long id;
    private String title;
    private String content;
    private String type;
    private Long timestamp;
    private String author;
}
