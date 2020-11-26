package pl.vmrent.web.controller.auth;

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
    private HttpServletRequest request;

    public String submit()
    {
        try
        {
            request.login(username, password);
        }
        catch (ServletException e)
        {
            return "root";
        }
        return "dashboard";
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
