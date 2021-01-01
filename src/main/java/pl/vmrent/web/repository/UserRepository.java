package pl.vmrent.web.repository;

import pl.vmrent.web.model.user.Role;
import pl.vmrent.web.model.user.User;
import pl.vmrent.web.repository.generator.UuidGenerator;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;
import java.util.Collections;
import java.util.UUID;

@ApplicationScoped
public class UserRepository extends InMemoryRepository<@Valid User, UUID>
{
    public UserRepository()
    {
        super(new UuidGenerator());
    }

    @PostConstruct
    private void init()
    {
        User u1 = new User("Nawrok", "trudnehaslo", "Sebastian Nawrocki", true, Collections.singleton(Role.Owner));
        User u2 = new User("Blazz", "trudnehaslo", "Maciej Błażewicz", true, Collections.singleton(Role.Admin));
        User u3 = new User("Malek", "trudnehaslo", "Bartłomiej Małkowski", true, Collections.singleton(Role.Client));
        save(u1);
        save(u2);
        save(u3);
    }
}
