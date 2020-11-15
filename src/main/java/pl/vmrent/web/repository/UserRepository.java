package pl.vmrent.web.repository;

import pl.vmrent.web.model.user.Role;
import pl.vmrent.web.model.user.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
public class UserRepository extends AbstractCrudRepository<User, String>
{
    @PostConstruct
    public void init()
    {
        save(new User("Nawrok", "trudnehaslo", "Sebastian Nawrocki", true, Stream.of(Role.USER, Role.ADMIN, Role.OWNER).collect(Collectors.toSet())));
        save(new User("Blazz", "trudnehaslo123", "Maciej Błażewicz", true, Stream.of(Role.USER, Role.ADMIN).collect(Collectors.toSet())));
    }

}
