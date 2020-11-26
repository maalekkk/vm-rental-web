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
    private List<User> users;

    @Inject
    private UserService userService;

    @PostConstruct
    private void init()
    {
        users = userService.getAll();
    }

    public void deleteUser(User user)
    {
        userService.deleteUser(user);
        users.remove(user);
    }

    public List<User> getUsers()
    {
        return users;
    }
}
