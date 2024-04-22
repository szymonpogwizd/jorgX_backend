package pl.jorgX.validator.password;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {

    String message() default "Password is not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
