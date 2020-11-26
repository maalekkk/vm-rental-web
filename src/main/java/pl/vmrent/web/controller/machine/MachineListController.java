package pl.vmrent.web.controller.machine;

import pl.vmrent.web.model.machine.Machine;
import pl.vmrent.web.service.MachineService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class MachineListController implements Serializable
{
    private List<Machine> machines;

    @Inject
    private MachineService machineService;

    @PostConstruct
    private void init()
    {
        machines = machineService.getAll();
    }

    public void deleteMachine(Machine machine)
    {
        machineService.deleteMachine(machine);
        //machines.remove(machine);
    }

    public List<Machine> getMachines()
    {
        return machines;
    }
}
