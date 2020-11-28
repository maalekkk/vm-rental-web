package pl.vmrent.web.controller.user;

import pl.vmrent.web.model.user.Role;
import pl.vmrent.web.model.user.User;
import pl.vmrent.web.service.UserService;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class CurrentUserController implements Serializable
{
    @Inject
    private UserService userService;

    public User getCurrentUser()
    {
        return userService.getCurrentUser();
    }

    public boolean isUserRole()
    {
        return userService.getCurrentRole() == Role.User;
    }

    public boolean isAdminRole()
    {
        return userService.getCurrentRole() == Role.Admin;
    }

    public boolean isOwnerRole()
    {
        return userService.getCurrentRole() == Role.Owner;
    }
}
