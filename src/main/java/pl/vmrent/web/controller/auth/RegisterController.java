package pl.vmrent.web.controller.auth;

import pl.vmrent.web.model.user.Role;
import pl.vmrent.web.model.user.User;
import pl.vmrent.web.service.UserService;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collections;

@Named
@ViewScoped
public class RegisterController implements Serializable
{
    private User user = new User();

    @Inject
    private UserService userService;

    public String submit()
    {
        user.setEnabled(true);
        user.setRoles(Collections.singleton(Role.USER));
        User addedUser = userService.addUser(user);
        if (addedUser != null)
        {
            return "/auth/login?faces-redirect=true";
        }
        return "/auth/register";
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

}
