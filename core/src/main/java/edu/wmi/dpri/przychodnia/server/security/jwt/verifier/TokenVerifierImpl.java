package edu.wmi.dpri.przychodnia.server.security.jwt.verifier;

import org.springframework.stereotype.Component;

/**
 * Created by lupus on 22.10.16.
 */
@Component
public class TokenVerifierImpl implements TokenVerifier{
    @Override
    public boolean verify(String jti) {
        //todo check for revoked token
        return true;
    }
}
