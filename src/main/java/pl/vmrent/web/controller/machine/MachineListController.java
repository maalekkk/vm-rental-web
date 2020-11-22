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
}
