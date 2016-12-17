package edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by kmazu on 06.07.2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class UserDataSimpleModel {
    private Long entityId;
    private Long userId;
    private String name;
    private String pesel;
    private String email;
    private String telephone;
    private String address;
    private String mailingAddress;
    private String entityType;


}
