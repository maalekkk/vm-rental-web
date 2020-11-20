package pl.vmrent.web.service;

import pl.vmrent.web.model.machine.Machine;
import pl.vmrent.web.model.machine.MachineGaming;
import pl.vmrent.web.model.machine.MachineWorkstation;
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

    public Machine addMachineGaming(@Valid MachineGaming machineGaming)
    {
        return machineRepository.save(machineGaming);
    }

    public Machine addMachineWorkstation(@Valid MachineWorkstation machineWorkstation)
    {
        return machineRepository.save(machineWorkstation);
    }

    public Optional<Machine> findMachineByName(String name)
    {
        return machineRepository.findByUniquePredicate(machine -> machine.getName().equals(name));
    }

    public List<Machine> getAll()
    {
        return machineRepository.findAll();
    }

    public <T extends Machine> boolean checkMachineType(@Valid Machine machine, Class<T> machineClass)
    {
        return machineClass.isInstance(machine);
    }
}
