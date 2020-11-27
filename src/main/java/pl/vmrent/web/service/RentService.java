package pl.vmrent.web.service;

import pl.vmrent.web.model.machine.Machine;
import pl.vmrent.web.model.rent.Rent;
import pl.vmrent.web.model.user.User;
import pl.vmrent.web.repository.RentRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequestScoped
public class RentService
{
    @Inject
    private RentRepository rentRepository;
    @Inject
    private MachineService machineService;
    @Inject
    private UserService userService;

    public Rent addRent(@Valid Rent rent)
    {
        return rentRepository.save(rent);
    }

    public List<Rent> findRentsByMachineName(String machineName)
    {
        Optional<Machine> optional = machineService.findMachineByName(machineName);
        if (optional.isPresent())
        {
            Machine machine = optional.get();
            return rentRepository.findByPredicate(rent -> rent.getMachine().equals(machine));
        }
        return Collections.emptyList();
    }

    public List<Rent> findRentsByUsername(String username)
    {
        Optional<User> optional = userService.findUserByUsername(username);
        if (optional.isPresent())
        {
            User user = optional.get();
            return rentRepository.findByPredicate(rent -> rent.getUser().equals(user));
        }
        return Collections.emptyList();
    }

    public List<Rent> getAll()
    {
        return rentRepository.findAll();
    }
}
