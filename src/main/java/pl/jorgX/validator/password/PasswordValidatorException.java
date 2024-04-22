package pl.jorgX.validator.password;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.EXPECTATION_FAILED, reason = "Password validation failed")
public class PasswordValidatorException extends RuntimeException {

    PasswordValidatorException(String message) {
        super(message);
    }
}
