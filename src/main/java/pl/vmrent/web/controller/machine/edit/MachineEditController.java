package pl.vmrent.web.controller.machine.edit;

import pl.vmrent.web.model.machine.Machine;
import pl.vmrent.web.service.MachineService;

import java.io.Serializable;

public abstract class MachineEditController<T extends Machine> implements Serializable
{
    private final MachineService machineService;
    private T machine;

    public MachineEditController(T machine, MachineService machineService)
    {
        this.machine = machine;
        this.machineService = machineService;
    }

    public String create()
    {
        machineService.addMachine(machine);
        return "/dashboard/show_vms?faces-redirect=true";
    }

    public T getMachine()
    {
        return machine;
    }

    public void setMachine(T machine)
    {
        this.machine = machine;
    }
}
