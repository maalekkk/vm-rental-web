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
    private MachineRepository machineRepository;

    public Machine saveMachine(@Valid Machine machine)
    {
        return machineRepository.save(machine);
    }

    public Optional<Machine> findMachineById(UUID machineId)
    {
        return machineRepository.findById(machineId);
    }

    public Optional<Machine> findMachineByName(String name)
    {
        return machineRepository.findByUniquePredicate(machine -> machine.getName().equals(name));
    }

    public List<Machine> getAll()
    {
        return machineRepository.findAll();
    }

    public void deleteMachine(Machine machine)
    {
        machineRepository.delete(machine);
    }
}
