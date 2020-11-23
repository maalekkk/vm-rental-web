package pl.vmrent.web.controller.machine;

import pl.vmrent.web.model.machine.Machine;
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
    private Machine machine;

    private List<Machine> machines;

    @Inject
    private MachineService machineService;

    @PostConstruct
    private void init()
    {
        machines = machineService.getAll();
        System.out.println("Xd");
    }

    public String deleteMachine()
    {
        return machineService.deleteMachine(machine.getId()) ? "" : "";
        //TODO ERROR PAGE
    }

    public List<Machine> getMachines()
    {
        return machines;
    }

    public Machine getMachine()
    {
        return machine;
    }

    public void setMachine(Machine machine)
    {
        this.machine = machine;
    }
}
