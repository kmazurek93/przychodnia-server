package edu.wmi.dpri.przychodnia.server.security.jwt;

/**
 * Created by lupus on 22.10.16.
 */
public interface JwtTokenExtractor {
    public String extract(String payload);
}
