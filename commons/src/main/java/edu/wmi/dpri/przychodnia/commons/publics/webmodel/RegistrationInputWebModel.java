package edu.wmi.dpri.przychodnia.commons.publics.webmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.PersonalDataWebModel;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.UserDataWebModel;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.creation.AddressCreationWebModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Created by kmazu on 02.07.2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class RegistrationInputWebModel {
    @NotNull
    private AddressCreationWebModel address;
    private AddressCreationWebModel mailingAddress;
    @NotNull
    private PersonalDataWebModel personalData;
    @NotNull
    private UserDataWebModel userData;

}
