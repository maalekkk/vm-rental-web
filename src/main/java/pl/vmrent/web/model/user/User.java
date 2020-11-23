package pl.vmrent.web.model.user;

import pl.vmrent.web.model.Entity;
import pl.vmrent.web.validator.unique.username.UniqueUsername;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

public class User extends Entity
{
    @UniqueUsername
    @Size(min = 3, max = 20)
    private String username;

    @Size(min = 8, max = 30)
    private String password;

    @NotBlank
    private String fullname;

    private boolean enabled;

    @NotEmpty
    private Set<String> roles;

    public User()
    {
    }

    public User(String username, String password, String fullname, Boolean enabled, Set<String> roles)
    {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.enabled = enabled;
        this.roles = roles;
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

    public String getFullname()
    {
        return fullname;
    }

    public void setFullname(String fullname)
    {
        this.fullname = fullname;
    }

    public boolean isEnabled()
    {
        return enabled;
    }

    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }

    public Set<String> getRoles()
    {
        return roles;
    }

    public void setRoles(Set<String> roles)
    {
        this.roles = roles;
    }
}
