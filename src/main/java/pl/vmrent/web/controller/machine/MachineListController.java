package pl.vmrent.web.controller.machine;

import pl.vmrent.web.model.machine.Machine;
import pl.vmrent.web.service.MachineService;
import pl.vmrent.web.service.RentService;

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
    @Inject
    private MachineService machineService;

    @Inject
    private RentService rentService;

    private List<Machine> machines;

    @PostConstruct
    public void init()
    {
        machines = machineService.getAll();
    }

    public String deleteMachine(Machine machine)
    {
        if (!rentService.isMachineRented(machine))
        {
            machineService.deleteMachine(machine);
            return "show_vms.xhtml?faces-redirect=true";
        }
        return null;
    }

    public boolean isRented(Machine machine)
    {
        return rentService.isMachineRented(machine);
    }

    public List<Machine> getMachines()
    {
        return machines;
    }
}
