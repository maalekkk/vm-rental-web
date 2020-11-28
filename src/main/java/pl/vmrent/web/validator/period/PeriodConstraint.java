package pl.vmrent.web.validator.period;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PeriodValidator.class)
public @interface PeriodConstraint
{
    String message() default "{period.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
