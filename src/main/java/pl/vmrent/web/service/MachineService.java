package pl.vmrent.web.service;

import pl.vmrent.web.model.machine.Machine;
import pl.vmrent.web.repository.MachineRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequestScoped
public class MachineService
{
    @Inject
    MachineRepository machineRepository;

    public Machine addMachine(@Valid Machine machine)
    {
        return machineRepository.save(machine);
    }

    public Optional<Machine> findMachineByName(String name)
    {
        return machineRepository.findByUniquePredicate(machine -> machine.getName().equals(name));
    }

    public List<Machine> getAll()
    {
        return machineRepository.findAll();
    }
}
