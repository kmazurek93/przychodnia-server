package edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by lupus on 18.10.16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class RegistrationOutput {

    private AddressWebModel address;
    private AddressWebModel mailingAddress;
    private PersonalDataWebModel personalDataWebModel;
    private UserDataWebModel userDataWebModel;

}
