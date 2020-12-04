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

    private String machineFilter = "";

    @PostConstruct
    public void init()
    {
        machines = machineService.getAll();
    }

    public String deleteMachine(Machine machine)
    {
        if (!rentService.isMachineAllocated(machine))
        {
            machineService.deleteMachine(machine);
            return "show-vms.xhtml?faces-redirect=true";
        }
        return null;
    }

    public void filter()
    {
        if (!machineFilter.isEmpty())
        {
            machines = machineService.filterMachineByName(machineFilter);
        }
        else
        {
            machines = machineService.getAll();
        }
    }

    public boolean isAllocated(Machine machine)
    {
        return rentService.isMachineAllocated(machine);
    }

    public List<Machine> getMachines()
    {
        return machines;
    }

    public String getMachineFilter()
    {
        return machineFilter;
    }

    public void setMachineFilter(String machineFilter)
    {
        this.machineFilter = machineFilter;
    }
}
