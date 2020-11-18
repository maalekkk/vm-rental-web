package pl.vmrent.web.service;

import pl.vmrent.web.model.user.User;
import pl.vmrent.web.repository.UserRepository;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.Optional;
import java.util.Set;

@RequestScoped
public class UserService
{
    @Inject
    UserRepository userRepository;

    public Optional<User> addUser(String username, String fullname, String password, Set<String> roles)
    {
        return Optional.of(userRepository.save(new User(username, password, fullname, true, roles)));
    }

    public Optional<User> findUserByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }

    public User getCurrentUser()
    {
        String currentUsername = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        return userRepository.findByUsername(currentUsername).orElse(null);
    }
}
