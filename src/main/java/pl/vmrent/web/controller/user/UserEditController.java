package pl.vmrent.web.controller.user;

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

    public String save()
    {
        //return userService.updateUser(user) ? "" : null;
        //TODO BACK TO LIST PAGE
        return null;
    }
}
