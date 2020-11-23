package pl.vmrent.web.service;

import pl.vmrent.web.exception.UserNotFoundException;
import pl.vmrent.web.model.user.User;
import pl.vmrent.web.repository.UserRepository;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
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
    PasswordService passwordService;

    public User addUser(@Valid User user)
    {
        user.setPassword(passwordService.hash(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<User> findUserByUsername(String username)
    {
        return userRepository.findByUniquePredicate(user -> user.getUsername().equals(username));
    }

    public User getCurrentUser()
    {
        String currentUsername = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        return findUserByUsername(currentUsername).orElseThrow(UserNotFoundException::new);
    }

    public List<User> getAll()
    {
        return userRepository.findAll();
    }

    public boolean updateUser(@Valid User user)
    {
        Optional<User> optional = userRepository.findById(user.getId());
        if (optional.isPresent())
        {
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public boolean deleteUser(UUID userId)
    {
        Optional<User> optional = userRepository.findById(userId);
        if (optional.isPresent())
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
