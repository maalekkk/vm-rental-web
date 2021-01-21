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
        User u4 = new User("ted", "trudnehaslo4", "Masny Ted", true, singleton(Client));
        User u5 = new User("ted2", "trudnehaslo4", "Masny Ted 2", true, singleton(Client));
        User u6 = new User("ted3", "trudnehaslo4", "Masny Ted 3", false, singleton(Client));
        User u7 = new User("ted4", "trudnehaslo4", "Masny Ted 4", true, singleton(Client));
        User u8 = new User("ted5", "trudnehaslo4", "Masny Ted 5", true, singleton(Client));
        User u9 = new User("ted6", "trudnehaslo4", "Masny Ted 6", true, singleton(Client));
        User u10 = new User("ted7", "trudnehaslo4", "Masny Ted 7", false, singleton(Client));
        User u11 = new User("ted8", "trudnehaslo4", "Masny Ted 8", false, singleton(Client));
        save(u1);
        save(u2);
        save(u3);
        save(u4);
        save(u5);
        save(u6);
        save(u7);
        save(u8);
        save(u9);
        save(u10);
        save(u11);
    }
}
