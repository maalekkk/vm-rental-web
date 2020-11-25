package pl.vmrent.web.repository;

import pl.vmrent.web.model.user.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;
import java.util.UUID;

@ApplicationScoped
public class UserRepository extends AbstractCrudRepository<@Valid User, UUID>
{
    @PostConstruct
    private void init()
    {
        User u1 = new User("Nawrok", "Sebastian Nawrocki", true);
        User u2 = new User("Blazz", "Maciej Błażewicz", true);
        User u3 = new User("Malek", "Bartłomiej Małkowski", true);
        save(u1);
        save(u2);
        save(u3);
    }
}
