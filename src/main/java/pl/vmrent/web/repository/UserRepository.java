package pl.vmrent.web.repository;

import pl.vmrent.web.model.user.Role;
import pl.vmrent.web.model.user.User;
import pl.vmrent.web.service.PasswordService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
public class UserRepository extends AbstractCrudRepository<User, UUID>
{
    @Inject
    private PasswordService passwordService;

    public Optional<User> findByUsername(String username)
    {
        return findByUniquePredicate(user -> user.getUsername().equals(username));
    }

    @PostConstruct
    public void init()
    {
        save(new User("Nawrok", password("trudnehaslo"), "Sebastian Nawrocki", true, Stream.of(Role.USER, Role.ADMIN, Role.OWNER).collect(Collectors.toSet())));
        save(new User("Blazz", password("trudnehaslo123"), "Maciej Błażewicz", true, Stream.of(Role.USER, Role.ADMIN).collect(Collectors.toSet())));
        save(new User("Malek", password("trudnehaslo456"), "Bartłomiej Małkowski", true, Stream.of(Role.USER, Role.ADMIN).collect(Collectors.toSet())));
    }

    private String password(String password)
    {
        return passwordService.hash(password);
    }
}
