package pl.jorgX.utils.jwt;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@Configuration
public class JwtUtils {

    private final SecretKey secretKey;

    public JwtUtils() {
        secretKey = generateSecretKey();
    }

    private SecretKey generateSecretKey() {
        return Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }

    public SecretKey getSecretKey() {
        return secretKey;
    }
}