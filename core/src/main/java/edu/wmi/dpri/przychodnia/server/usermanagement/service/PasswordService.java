package edu.wmi.dpri.przychodnia.server.usermanagement.service;

import edu.wmi.dpri.przychodnia.server.entity.User;
import org.springframework.stereotype.Service;

import javax.ws.rs.InternalServerErrorException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;


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

    public Boolean isLoginValid(String base64EncodedPassword, User user) {
        String saltedHash = createSaltedHash(base64EncodedPassword, user.getLogin());
        return user.getPassword().equals(saltedHash);
    }

    public String createSaltedHash(String base64EncodedPasswd, String salt) {
        byte[] base64decoded = getBase64DecodedBytes(base64EncodedPasswd);
        byte[] saltBytes = salt.getBytes();
        messageDigest.update(saltBytes);
        byte[] digest = messageDigest.digest(base64decoded);
        byte[] base64Encoded = getBase64EncodedBytes(digest);
        return new String(base64Encoded);
    }

    private byte[] getBase64EncodedBytes(byte[] digest) {
        Encoder encoder = Base64.getEncoder();
        return encoder.encode(digest);
    }

    private byte[] getBase64DecodedBytes(String base64EncodedPasswd) {
        Base64.Decoder decoder = getBase64Decoder();
        return decoder.decode(base64EncodedPasswd);
    }

    private Base64.Decoder getBase64Decoder() {
        return Base64.getDecoder();
    }
}
