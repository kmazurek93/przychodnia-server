package edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.creation;

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
public class UserLinkingUnlinkingWebModel {

    @NotNull
    private String parentPesel;
    @NotNull
    private String childPesel;

}
