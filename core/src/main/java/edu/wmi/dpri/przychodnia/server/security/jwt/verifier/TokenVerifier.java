package edu.wmi.dpri.przychodnia.server.security.jwt.verifier;

/**
 * Created by lupus on 22.10.16.
 */
public interface TokenVerifier {
    boolean verify(String jti);
}
