package pl.vmrent.web.repository;

import pl.vmrent.web.model.rent.Rent;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
public class RentRepository extends AbstractCrudRepository<Rent, UUID>
{

}
