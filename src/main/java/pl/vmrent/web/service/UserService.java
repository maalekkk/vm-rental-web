package pl.vmrent.web.service;

import pl.vmrent.web.model.user.Role;
import pl.vmrent.web.model.user.User;
import pl.vmrent.web.repository.UserRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestScoped
public class UserService
{
    @Inject
    private UserRepository userRepository;

    @Inject
    private HttpServletRequest request;

    public User saveUser(@Valid User user)
    {
        return userRepository.save(user);
    }

    public Optional<User> findUserById(UUID userId)
    {
        return userRepository.findById(userId);
    }

    public Optional<User> findUserByUsername(String username)
    {
        return userRepository.findByUniquePredicate(user -> user.getUsername().equals(username));
    }

    public User getCurrentUser()
    {
        return findUserByUsername(request.getRemoteUser()).orElse(null);
    }

    public Role getCurrentRole()
    {
        if (request.isUserInRole(Role.User.name()))
        {
            return Role.User;
        }
        if (request.isUserInRole(Role.Admin.name()))
        {
            return Role.Admin;
        }
        if (request.isUserInRole(Role.Owner.name()))
        {
            return Role.Owner;
        }
        return null;
    }

    public List<User> getAll()
    {
        return userRepository.findAll();
    }

    public void deleteUser(User user)
    {
        userRepository.delete(user);
    }

    public void changeUserActivity(User user)
    {
        user.setEnabled(!user.isEnabled());
        userRepository.save(user);
    }
}
