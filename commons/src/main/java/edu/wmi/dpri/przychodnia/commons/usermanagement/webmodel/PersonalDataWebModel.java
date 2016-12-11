package edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Created by lupus on 18.10.16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class PersonalDataWebModel {
    @NotNull
    private String pesel;
    @NotNull
    private String firstName;
    private String middleName;
    @NotNull
    private String lastName;
    @NotNull
    private Long birthDate;
    @NotNull
    private String birthPlace;
    @NotNull
    private String idNumber;
    @NotNull
    private Long idTypeNo;
    @NotNull
    private Long sexId;
    private String telephone;

}
