package pl.vmrent.web.validator.period;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = PeriodConstraintValidator.class)
public @interface PeriodConstraint
{
    String message() default "{period.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
