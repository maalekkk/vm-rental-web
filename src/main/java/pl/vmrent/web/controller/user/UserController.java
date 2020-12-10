package pl.vmrent.web.controller.user;

import pl.vmrent.web.model.user.User;
import pl.vmrent.web.service.UserService;
import pl.vmrent.web.validator.unique.username.UniqueUsername;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Named
@ViewScoped
public class UserController implements Serializable
{
    @Inject
    private UserService userService;

    @UniqueUsername
    @Size(min = 3, max = 20)
    private String username;

    private User user = new User();

    public void initUser()
    {
        userService.findUserById(user.getId()).ifPresent(u -> user = u);
    }

    public String submit()
    {
        if (user.getUsername() == null)
        {
            user.setUsername(username);
        }
        userService.saveUser(user);
        return "show-users.xhtml?faces-redirect=true";
    }

    public boolean isUpdate()
    {
        return userService.existsUser(user);
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }
}
