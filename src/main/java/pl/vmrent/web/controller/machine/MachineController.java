package pl.vmrent.web.controller.machine;

import pl.vmrent.web.model.machine.Machine;
import pl.vmrent.web.service.MachineService;

import javax.validation.Valid;
import java.io.Serializable;

public abstract class MachineController<T extends Machine> implements Serializable
{
    protected final MachineService machineService;

    @Valid
    protected T machine;

    public MachineController(T machine, MachineService machineService)
    {
        this.machine = machine;
        this.machineService = machineService;
    }

    public abstract String submit();

    public T getMachine()
    {
        return machine;
    }

    public void setMachine(T machine)
    {
        this.machine = machine;
    }
}
