package edu.wmi.dpri.przychodnia.server.security.webapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RefreshTokenModel {
    private String refreshToken;
}
