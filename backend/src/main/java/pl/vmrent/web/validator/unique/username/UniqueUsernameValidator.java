package pl.vmrent.web.validator.unique.username;

import pl.vmrent.web.model.user.User;
import pl.vmrent.web.service.UserService;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, User>
{
    @Inject
    private UserService userService;

    @Override
    public boolean isValid(User user, ConstraintValidatorContext constraintValidatorContext)
    {
        return user.getId() != null || !userService.findUserByUsername(user.getUsername()).isPresent();
    }
}
