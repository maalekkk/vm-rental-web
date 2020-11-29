package pl.vmrent.web.controller.auth;

import pl.vmrent.web.service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Named
@RequestScoped
public class LoginController
{
    String username;
    String password;

    @Inject
    private UserService userService;

    @Inject
    private HttpServletRequest request;

    public String submit()
    {
        if (userService.findUserByUsername(username).isPresent())
        {
            try
            {
                request.login(username, password);
              return "dashboard";
            }
            catch (ServletException ignored)
            {
                //TODO LOGGER
            }
        }
        return "root";
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
