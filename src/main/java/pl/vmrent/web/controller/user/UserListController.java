package pl.vmrent.web.controller.user;

import pl.vmrent.web.model.user.User;
import pl.vmrent.web.service.UserService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Named
@ViewScoped
public class UserListController implements Serializable
{
    private static final List<Integer> sizes = Arrays.asList(2, 3, 5);

    @Inject
    private UserService userService;

    private List<User> users;

    private String userFilter = "";

    private int page = 1;
    private int size = sizes.get(sizes.size() - 1);

    @PostConstruct
    private void init()
    {
        users = userService.getAll(page, size);
    }

    public void changeUserActivity(User user)
    {
        userService.changeUserActivity(user);
    }

    public void filter()
    {
        if (!userFilter.isEmpty())
        {
            users = userService.filterUserByUsername(userFilter, page, size);
        }
        else
        {
            users = userService.getAll(page, size);
        }
    }

    public void nextPage()
    {
        page += 1;
    }

    public void previousPage()
    {
        page -= 1;
    }

    public boolean isStart()
    {
        return page == 1;
    }

    public boolean isEnd()
    {
        if (!userFilter.isEmpty())
        {
            return userService.filterUserByUsername(userFilter, page + 1, size).isEmpty();
        }
        return userService.getAll(page + 1, size).isEmpty();
    }

    public List<User> getUsers()
    {
        return users;
    }

    public String getUserFilter()
    {
        return userFilter;
    }

    public void setUserFilter(String userFilter)
    {
        this.userFilter = userFilter;
    }

    public List<Integer> getSizes()
    {
        return sizes;
    }

    public int getPage()
    {
        return page;
    }

    public void setPage(int page)
    {
        this.page = page;
    }

    public int getSize()
    {
        return size;
    }

    public void setSize(int size)
    {
        this.size = size;
    }
}
