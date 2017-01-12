package edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class UserCrudWebModel {
    @NotNull
    @Valid
    private AddressWebModel address;
    private AddressWebModel mailingAddress;
    @NotNull
    @Valid
    private PersonalDataWebModel personalData;
    @NotNull
    @Valid
    private UserDataWebModel userData;

}
