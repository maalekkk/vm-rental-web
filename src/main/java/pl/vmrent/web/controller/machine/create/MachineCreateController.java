package pl.vmrent.web.controller.machine.create;

import pl.vmrent.web.controller.machine.MachineController;
import pl.vmrent.web.model.machine.Machine;
import pl.vmrent.web.service.MachineService;

public abstract class MachineCreateController<T extends Machine> extends MachineController<T>
{
    public MachineCreateController(T machine, MachineService machineService)
    {
        super(machine, machineService);
    }

    @Override
    public String submit()
    {
        return machineService.addMachine(machine).isPresent() ? "/dashboard/show_vms?faces-redirect=true" : "";
        //TODO ERROR PAGE
    }
}
