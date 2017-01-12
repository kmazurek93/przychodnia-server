package edu.wmi.dpri.przychodnia.server.security.jwt.verifier;


public interface TokenVerifier {
    boolean verify(String jti);
}
