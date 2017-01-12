package edu.wmi.dpri.przychodnia.server.security.jwt.model;



import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.ErrorMessage;
import edu.wmi.dpri.przychodnia.server.exceptionmanagement.exceptions.auth.AuthenticationAppException;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static edu.wmi.dpri.przychodnia.server.exceptionmanagement.generators.ErrorMessageGenerator.getAuthenticationErrorMessage;

public class RawAccessJwtToken implements JwtToken {
    private static final String TOKEN_INVALID = "TOKEN_INVALID";
    private static final String TOKEN_EXPIRED = "TOKEN_EXPIRED";
    private static Logger logger = LoggerFactory.getLogger(RawAccessJwtToken.class);

    private String token;

    public RawAccessJwtToken(String token) {
        this.token = token;
    }

    public Jws<Claims> parseClaims(String signingKey) {
        try {
            return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(this.token);
        } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException | SignatureException ex) {
            logger.error("Invalid JWT Token", ex);
            ErrorMessage errorMessage = getAuthenticationErrorMessage(TOKEN_INVALID);
            throw new AuthenticationAppException(errorMessage);
        } catch (ExpiredJwtException expiredEx) {
            logger.info("JWT Token is expired", expiredEx);
            ErrorMessage errorMessage = getAuthenticationErrorMessage(TOKEN_EXPIRED);
            throw new AuthenticationAppException(errorMessage);
        }
    }

    @Override
    public String getToken() {
        return token;
    }
}