package pl.vmrent.web.service;

import pl.vmrent.web.model.machine.Machine;
import pl.vmrent.web.model.rent.Rent;
import pl.vmrent.web.model.user.User;
import pl.vmrent.web.repository.RentRepository;
import pl.vmrent.web.util.DateTimeProvider;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestScoped
public class RentService
{
    @Inject
    private RentRepository rentRepository;

    @Inject
    private DateTimeProvider dateTimeProvider;

    public Rent saveRent(@Valid Rent rent)
    {
        return rentRepository.save(rent);
    }

    public List<Rent> findRentsByMachine(Machine machine)
    {
        return rentRepository.findByPredicate(rent -> rent.getMachine().equals(machine));
    }

    public List<Rent> findRentsByUser(User user)
    {
        return rentRepository.findByPredicate(rent -> rent.getUser().equals(user));
    }

    public List<Rent> getAll()
    {
        return rentRepository.findAll();
    }

    public void deleteRent(Rent rent)
    {
        if (!isRentFinished(rent))
        {
            rentRepository.delete(rent);
        }
    }

    public boolean isMachineRented(Machine machine)
    {
        return rentRepository.findByPredicate(rent -> !isRentFinished(rent)).stream().anyMatch(r -> r.getMachine().equals(machine));
    }

    public boolean isRentFinished(Rent rent)
    {
        return rent.getPeriod().getEndDate().isBefore(dateTimeProvider.now());
    }

    public Optional<Rent> findRentById(UUID fromString) {
        return rentRepository.findById(fromString);
    }
}
