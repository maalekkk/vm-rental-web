package pl.vmrent.web.service;

import pl.vmrent.web.exception.UserNotFoundException;
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
    UserRepository userRepository;

    @Inject
    HttpServletRequest request;

    public User addUser(@Valid User user)
    {
        return userRepository.save(user);
    }

    public Optional<User> findUserByUsername(String username)
    {
        return userRepository.findByUniquePredicate(user -> user.getUsername().equals(username));
    }

    public User getCurrentUser()
    {
        return findUserByUsername(request.getRemoteUser()).orElseThrow(UserNotFoundException::new);
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

    public boolean updateUser(@Valid User user)
    {
        if (userRepository.existsById(user.getId()))
        {
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public boolean deleteUser(UUID userId)
    {
        if (userRepository.existsById(userId))
        {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public boolean changeUserActivity(@Valid User user)
    {
        user.setEnabled(!user.isEnabled());
        return updateUser(user);
    }
}
