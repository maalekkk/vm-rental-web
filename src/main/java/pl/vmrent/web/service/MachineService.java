package pl.vmrent.web.service;

import pl.vmrent.web.model.machine.Machine;
import pl.vmrent.web.repository.MachineRepository;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@ViewScoped
public class MachineService implements Serializable
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
        if (machineRepository.existsById(machine.getId()))
        {
            machineRepository.save(machine);
            return true;
        }
        return false;
    }

    public boolean deleteMachine(Machine machine)
    {
        return machineRepository.delete(machine);
    }
}
