package pl.vmrent.web.controller;

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
public class UserController implements Serializable
{
    @Inject
    private UserService userService;

    private User registerUser = new User();

    public String registerUser()
    {
        userService.createUser(registerUser.getUsername(), registerUser.getFullname(), registerUser.getPassword(), Collections.singleton(Role.USER));
        return "/auth/login";
    }

    public User getRegisterUser()
    {
        return registerUser;
    }

    public void setRegisterUser(User registerUser)
    {
        this.registerUser = registerUser;
    }
}
