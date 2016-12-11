package edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Created by kmazu on 06.07.2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class AddressWebModel {

    private Long id;
    @NotNull
    private String country;
    private String province;
    @NotNull
    private String city;
    @NotNull
    private String street;
    @NotNull
    private String house;
    private String apartment;
    @NotNull
    private String postCode;

}
