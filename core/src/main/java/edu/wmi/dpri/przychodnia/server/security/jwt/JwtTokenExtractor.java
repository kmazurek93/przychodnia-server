package edu.wmi.dpri.przychodnia.server.security.jwt;


public interface JwtTokenExtractor {
    String extract(String payload);
}
