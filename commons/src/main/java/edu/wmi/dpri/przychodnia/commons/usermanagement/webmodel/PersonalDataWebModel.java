package edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


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
