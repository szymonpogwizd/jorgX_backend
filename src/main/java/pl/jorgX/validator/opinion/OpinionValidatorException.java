package pl.jorgX.validator.opinion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.EXPECTATION_FAILED, reason = "Opinion validation failed")
public class OpinionValidatorException  extends  RuntimeException {

    OpinionValidatorException(String message)
    {
        super(message);
    }
}
