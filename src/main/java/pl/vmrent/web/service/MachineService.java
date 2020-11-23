package pl.vmrent.web.service;

import pl.vmrent.web.model.machine.Machine;
import pl.vmrent.web.repository.MachineRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestScoped
public class MachineService
{
    @Inject
    MachineRepository machineRepository;

    public Optional<Machine> addMachine(@Valid Machine machine)
    {
        return Optional.of(machineRepository.save(machine));
    }

    public Optional<Machine> findMachineByName(String name)
    {
        return machineRepository.findByUniquePredicate(machine -> machine.getName().equals(name));
    }

    public List<Machine> getAll()
    {
        return machineRepository.findAll();
    }

    public boolean updateMachine(@Valid Machine machine)
    {
        Optional<Machine> optional = machineRepository.findById(machine.getId());
        if (optional.isPresent())
        {
            machineRepository.save(machine);
            return true;
        }
        return false;
    }

    public boolean deleteMachine(UUID machineId)
    {
        Optional<Machine> optional = machineRepository.findById(machineId);
        if (optional.isPresent())
        {
            machineRepository.deleteById(machineId);
            return true;
        }
        return false;
    }
}
