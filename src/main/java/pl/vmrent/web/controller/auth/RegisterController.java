package pl.vmrent.web.controller.auth;

import pl.vmrent.web.model.user.Role;
import pl.vmrent.web.model.user.User;
import pl.vmrent.web.service.PasswordService;
import pl.vmrent.web.service.UserService;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.Collections;
import java.util.Optional;

@Named
@ViewScoped
public class RegisterController implements Serializable
{
    @Valid
    private User registerUser = new User();

    @Inject
    private UserService userService;

    @Inject
    private PasswordService passwordService;

    public String submit()
    {
        Optional<User> optionalUser = userService.addUser(
                registerUser.getUsername(),
                registerUser.getFullname(),
                passwordService.hash(registerUser.getPassword()),
                Collections.singleton(Role.USER));
        if (optionalUser.isPresent())
        {
            return "/auth/login?faces-redirect=true";
        }
        return "/auth/register";
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
