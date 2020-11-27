package pl.vmrent.web.controller.user;

import pl.vmrent.web.model.user.User;
import pl.vmrent.web.service.UserService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class UserListController implements Serializable
{
    @Inject
    private UserService userService;

    private List<User> users;

    @PostConstruct
    private void init()
    {
        users = userService.getAll();
    }

    public String changeUserActivity(User user)
    {
        userService.changeUserActivity(user);
        return "show_users.xhtml?faces-redirect=true";
    }

    public String deleteUser(User user)
    {
        userService.deleteUser(user);
        return "show_users.xhtml?faces-redirect=true";
    }

    public List<User> getUsers()
    {
        return users;
    }
}
