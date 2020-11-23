package pl.vmrent.web.controller.machine.edit;

import pl.vmrent.web.controller.machine.MachineController;
import pl.vmrent.web.model.machine.Machine;
import pl.vmrent.web.service.MachineService;

public abstract class MachineEditController<T extends Machine> extends MachineController<T>
{
    public MachineEditController(T machine, MachineService machineService)
    {
        super(machine, machineService);
    }

    @Override
    public String submit()
    {
        return machineService.updateMachine(machine) ? "/dashboard/show_vms?faces-redirect=true" : "";
        //TODO ERROR PAGE
    }
}
