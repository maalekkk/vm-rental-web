package pl.vmrent.web.repository;

import pl.vmrent.web.model.user.User;
import pl.vmrent.web.repository.generator.UuidGenerator;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;
import java.util.UUID;

import static java.util.Collections.singleton;
import static pl.vmrent.web.model.user.Role.*;

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
        User u1 = new User("Nawrok", "trudnehaslo", "Sebastian Nawrocki", true, singleton(Owner));
        User u2 = new User("Blazz", "trudnehaslo2", "Maciej Błażewicz", true, singleton(Admin));
        User u3 = new User("Malek", "trudnehaslo3", "Bartłomiej Małkowski", true, singleton(Client));
        User u4 = new User("Rafon", "trudnehaslo4", "Marcin Krasucki", false, singleton(Client));
        save(u1);
        save(u2);
        save(u3);
        save(u4);
    }
}
