package pl.vmrent.web.repository;

import pl.vmrent.web.model.rent.Rent;
import pl.vmrent.web.repository.generator.UuidGenerator;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class RentRepository extends InMemoryRepository<@Valid Rent, UUID>
{
    public RentRepository()
    {
        super(new UuidGenerator());
    }

    @Override
    public List<Rent> findAll()
    {
        return super.findAll().stream().sorted(Comparator.comparing((Rent rent) -> rent.getPeriod().getStartDate()).reversed()).collect(Collectors.toList());
    }
}
