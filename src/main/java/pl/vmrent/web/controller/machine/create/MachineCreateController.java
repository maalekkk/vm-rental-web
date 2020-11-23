package pl.vmrent.web.controller.machine.create;

import pl.vmrent.web.controller.machine.MachineController;
import pl.vmrent.web.model.machine.Machine;
import pl.vmrent.web.service.MachineService;

import java.util.Optional;

public abstract class MachineCreateController<T extends Machine> extends MachineController<T>
{
    public MachineCreateController(T machine, MachineService machineService)
    {
        super(machine, machineService);
    }

    @Override
    public String submit()
    {
        Optional<Machine> optional = machineService.addMachine(machine);
        return optional.isPresent() ? "/dashboard/show_vms?faces-redirect=true" : "";
        //TODO ERROR PAGE
    }
}
