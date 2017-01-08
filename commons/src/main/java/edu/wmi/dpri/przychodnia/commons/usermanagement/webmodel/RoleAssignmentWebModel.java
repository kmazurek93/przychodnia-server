package edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by lupus on 24.11.16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class RoleAssignmentWebModel {

    @NotNull
    private Long userId;
    @NotNull
    private List<String> roles;
}
