package pl.vmrent.web.controller.auth;

import pl.vmrent.web.service.UserService;
import pl.vmrent.web.util.MessageProvider;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@RequestScoped
public class LoginController
{
    private static final Logger logger = Logger.getLogger(LoginController.class.getSimpleName());

    String username;
    String password;

    @Inject
    private UserService userService;

    @Inject
    private HttpServletRequest request;

    @Inject
    private MessageProvider messageProvider;

    public String submit()
    {
        return userService.findUserByUsername(username).map(user ->
        {
            if (user.isEnabled())
            {
                try
                {
                    request.login(username, password);
                    logger.log(Level.INFO, username + " " + messageProvider.getMessage("messages", "login_succeed"));
                    return "dashboard";
                }
                catch (ServletException ignored)
                {
                    logger.log(Level.WARNING, username + " " + messageProvider.getMessage("messages", "login_failed"));
                    return null;
                }
            }
            logger.log(Level.INFO, username + " " + messageProvider.getMessage("messages", "login_restricted"));
            return null;
        }).orElse("root");
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
