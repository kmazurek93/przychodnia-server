package edu.wmi.dpri.przychodnia.server.security.jwt.verifier;

import org.springframework.stereotype.Component;


@Component
public class TokenVerifierImpl implements TokenVerifier{
    @Override
    public boolean verify(String jti) {
        return true;
    }
}
