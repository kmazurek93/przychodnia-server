package edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class RoleWebModel {

    private Integer id;
    @NotNull
    private String name;

}
