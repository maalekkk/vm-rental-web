package pl.vmrent.web.controller.machine;

import pl.vmrent.web.model.machine.Machine;
import pl.vmrent.web.model.machine.MachineGaming;
import pl.vmrent.web.model.machine.MachineWorkstation;
import pl.vmrent.web.service.MachineService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class MachineListController
{
    private List<Machine> machines;

    @Inject
    private MachineService machineService;

    @PostConstruct
    private void init()
    {
        machines = machineService.getAll();
    }

    public List<Machine> getMachines()
    {
        return machines;
    }

    public boolean isMachineGaming(Machine machine)
    {
        return machineService.checkMachineType(machine, MachineGaming.class);
    }

    public boolean isMachineWorkstation(Machine machine)
    {
        return machineService.checkMachineType(machine, MachineWorkstation.class);
    }
}
