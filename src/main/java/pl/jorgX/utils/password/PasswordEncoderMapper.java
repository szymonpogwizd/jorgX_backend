package pl.jorgX.utils.password;

import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderMapper {

    protected final PasswordEncoder passwordEncoder;

    public PasswordEncoderMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @EncodedMapping
    public String encode(String value) {
        return passwordEncoder.encode(value);
    }
}