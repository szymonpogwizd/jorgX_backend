package pl.jorgX.validator.opinion;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class OpinionValidator implements ConstraintValidator<Opinion, String> {

    private static final Pattern FORBIDDEN_CHARACTERS =
            Pattern.compile("[<>/&;*|#%${}=+\\^_~`@]|[\\x00-\\x1F\\x7F]");

    @Override
    public void initialize(Opinion constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            validate(value);
            return true;
        } catch (OpinionValidatorException e) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addConstraintViolation();
            return false;
        }
    }

    public static void validate(String opinion) {
        if (opinion == null || opinion.isBlank()) {
            throw new OpinionValidatorException("Opinia nie może być pusta.");
        }

        String cleanedOpinion = FORBIDDEN_CHARACTERS.matcher(opinion).replaceAll("*");

        if (cleanedOpinion.trim().isEmpty()) {
            throw new OpinionValidatorException("Opinia po usunięciu niedozwolonych znaków jest pusta.");
        }

        if (cleanedOpinion.length() > 500) {
            throw new OpinionValidatorException("Opinia jest dłuższa niż 500 znaków.");
        }
    }
}