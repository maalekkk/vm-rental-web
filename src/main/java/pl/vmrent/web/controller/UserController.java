package pl.vmrent.web.controller;

import pl.vmrent.web.model.user.User;
import pl.vmrent.web.service.UserService;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class UserController implements Serializable
{
    @Inject
    private UserService userService;

    public User getCurrentUser()
    {
        return userService.getCurrentUser();
    }
}
