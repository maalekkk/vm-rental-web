package pl.vmrent.web.service;

import pl.vmrent.web.model.user.User;
import pl.vmrent.web.repository.UserRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Optional;
import java.util.Set;

@RequestScoped
public class UserService implements Serializable
{
    @Inject
    UserRepository userRepository;

    public Optional<User> findUserByUsername(String username)
    {
        return userRepository.findById(username);
    }

    public User createUser(String username, String fullname, String password, Set<String> roles)
    {
        return userRepository.save(new User(username, password, fullname, true, roles));
    }

}
