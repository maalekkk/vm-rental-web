package pl.vmrent.web.repository;

import pl.vmrent.web.model.rent.Rent;
import pl.vmrent.web.repository.generator.UuidGenerator;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;
import java.util.UUID;

@ApplicationScoped
public class RentRepository extends InMemoryRepository<@Valid Rent, UUID>
{
    public RentRepository()
    {
        super(new UuidGenerator());
    }
}
