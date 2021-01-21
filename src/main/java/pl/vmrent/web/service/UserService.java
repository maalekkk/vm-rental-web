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

    public List<User> filterUserByUsername(String username)
    {
        return userRepository.findByPredicate(user -> user.getUsername().contains(username));
    }

    public List<User> filterUserByUsername(String username, int page, int size)
    {
        return userRepository.findByPredicate(user -> user.getUsername().contains(username), page, size);
    }

    public User getCurrentUser()
    {
        return findUserByUsername(request.getRemoteUser()).orElseThrow(IllegalStateException::new);
    }

    public Role getCurrentRole()
    {
        if (request.isUserInRole(Role.Client.name()))
        {
            return Role.Client;
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

    public boolean existsUser(User user)
    {
        return userRepository.existsById(user.getId());
    }

    public List<User> getAll()
    {
        return userRepository.findAll();
    }

    public List<User> getAll(int page, int size)
    {
        return userRepository.findAll(page, size);
    }

    public void changeUserActivity(User user)
    {
        user.setEnabled(!user.isEnabled());
        userRepository.save(user);
    }
}
