package pl.vmrent.web.repository.memory;

import pl.vmrent.web.model.user.User;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository extends AbstractCrudRepository<User, String>
{

}
