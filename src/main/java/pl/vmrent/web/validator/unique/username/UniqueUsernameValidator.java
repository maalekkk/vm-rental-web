package pl.vmrent.web.validator.unique.username;

import pl.vmrent.web.service.UserService;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String>
{
    @Inject
    UserService userService;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext)
    {
        return !userService.findUserByUsername(s).isPresent();
    }
}
