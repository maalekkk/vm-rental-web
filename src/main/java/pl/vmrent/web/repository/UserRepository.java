package pl.vmrent.web.repository;

import pl.vmrent.web.model.user.Role;
import pl.vmrent.web.model.user.User;
import pl.vmrent.web.service.PasswordService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
public class UserRepository extends AbstractCrudRepository<User, UUID>
{
    @Inject
    private PasswordService passwordService;

    @PostConstruct
    private void init()
    {
        User u1 = new User("Nawrok", password("trudnehaslo"), "Sebastian Nawrocki", true, Stream.of(Role.OWNER).collect(Collectors.toSet()));
        User u2 = new User("Blazz", password("trudnehaslo123"), "Maciej Błażewicz", true, Stream.of(Role.ADMIN).collect(Collectors.toSet()));
        User u3 = new User("Malek", password("trudnehaslo456"), "Bartłomiej Małkowski", true, Stream.of(Role.ADMIN).collect(Collectors.toSet()));
        save(u1);
        save(u2);
        save(u3);
    }

    private String password(String password)
    {
        return passwordService.hash(password);
    }
}
