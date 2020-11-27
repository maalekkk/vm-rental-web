package pl.vmrent.web.controller.user;

import pl.vmrent.web.model.user.User;
import pl.vmrent.web.service.UserService;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class UserEditController implements Serializable
{
    @Inject
    private UserService userService;

    private User user = new User();

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public String submit()
    {
        userService.saveUser(user);
        return "show_users.xhtml?faces-redirect=true";
    }
}
