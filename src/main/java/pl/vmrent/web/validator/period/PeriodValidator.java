package pl.vmrent.web.validator.period;

import pl.vmrent.web.model.rent.Period;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PeriodValidator implements ConstraintValidator<PeriodConstraint, Period>
{
    @Override
    public boolean isValid(Period period, ConstraintValidatorContext constraintValidatorContext)
    {
        return period.getStartDate().isBefore(period.getEndDate());
    }
}
