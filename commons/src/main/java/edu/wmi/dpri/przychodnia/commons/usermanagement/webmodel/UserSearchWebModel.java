package edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by lupus on 14.11.16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class UserSearchWebModel {

    private Integer page;
    private Integer size;
    private String name;
    private String mail;
    private String address;
    private String telephone;
    private String pesel;
    private String searchType;

}
