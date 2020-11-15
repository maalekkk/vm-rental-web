package pl.vmrent.web.model.user;

import pl.vmrent.web.repository.Identity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

public class User implements Identity<String>
{
    @NotEmpty
    @Size(min = 3)
    private String username;

    @NotEmpty
    @Size(min = 8)
    private String password;

    @NotEmpty
    private String fullname;

    @NotNull
    private boolean enabled;

    @NotEmpty
    private Set<String> roles;

    public User()
    {
    }

    public User(String username, String password, String fullname, boolean enabled, Set<String> roles)
    {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.enabled = enabled;
        this.roles = roles;
    }

    @Override
    public String getId()
    {
        return getUsername();
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

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        User user = (User) o;
        return username.equals(user.username);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(username);
    }
}
