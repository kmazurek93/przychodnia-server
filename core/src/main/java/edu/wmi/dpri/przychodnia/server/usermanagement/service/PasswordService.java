package edu.wmi.dpri.przychodnia.server.usermanagement.service;

import org.springframework.stereotype.Service;

import javax.ws.rs.InternalServerErrorException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;

/**
 * Created by lupus on 21.10.16.
 */
@Service
public class PasswordService {

    public static final String MD_5 = "md5";
    private MessageDigest messageDigest;

    public PasswordService() {
        try {
            messageDigest = MessageDigest.getInstance(MD_5);
        } catch (NoSuchAlgorithmException e) {
            throw new InternalServerErrorException("Cannot create MD5 hasher...");
        }
    }

    public String createSaltedHash(String value, String salt) {
        byte[] valueBytes = value.getBytes();
        byte[] saltBytes = salt.getBytes();
        messageDigest.update(saltBytes);
        byte[] digest = messageDigest.digest(valueBytes);
        Encoder encoder = Base64.getEncoder();
        byte[] base64Encoded = encoder.encode(digest);
        return new String(base64Encoded);
    }
}
