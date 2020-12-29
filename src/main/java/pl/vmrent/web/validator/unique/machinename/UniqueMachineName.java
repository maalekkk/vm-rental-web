package pl.vmrent.web.validator.unique.machinename;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, ElementType.FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = UniqueMachineNameValidator.class)
public @interface UniqueMachineName
{
    String message() default "{unique.machinename.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
