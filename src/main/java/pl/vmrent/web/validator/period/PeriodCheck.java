package pl.vmrent.web.validator.period;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = PeriodValidator.class)
public @interface PeriodCheck
{
    String message() default "{period.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
