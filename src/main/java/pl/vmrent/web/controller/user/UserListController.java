package pl.vmrent.web.controller.user;

import pl.vmrent.web.model.user.User;
import pl.vmrent.web.service.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class UserListController
{
    private List<User> users;

    @Inject
    private UserService userService;

    @PostConstruct
    private void init()
    {
        users = userService.getAll();
    }

    public List<User> getUsers()
    {
        return users;
    }
}
