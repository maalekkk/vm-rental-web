package pl.vmrent.web.model.user;

import pl.vmrent.web.repository.memory.Identity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.StringJoiner;

public class User implements Identity<String>
{
    @NotNull
    @Size(min = 3)
    private String username;

    @NotNull
    @Size(min = 8)
    private String password;

    @NotNull
    private String fullname;

    private boolean enabled;

    public User()
    {
    }

    public User(String login, String password, String fullname, boolean enabled)
    {
        this.username = login;
        this.password = password;
        this.fullname = fullname;
        this.enabled = enabled;
    }

    @Override
    public String getId()
    {
        return getUsername();
    }

    @Override
    public void setId(String s)
    {
        setUsername(s);
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

    @Override
    public String toString()
    {
        return new StringJoiner(" | ", "", "")
                .add("login: '" + username + "'")
                .add("fullname: '" + fullname + "'")
                .add("enabled: " + enabled)
                .toString();
    }
}
