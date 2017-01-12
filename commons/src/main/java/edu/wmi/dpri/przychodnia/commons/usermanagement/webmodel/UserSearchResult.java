package edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class UserSearchResult {
    private Integer amountOfPages;
    private List<UserDataSimpleModel> users;

}
