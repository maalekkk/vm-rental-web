package pl.vmrent.web.controller.machine;

import pl.vmrent.web.model.machine.Machine;
import pl.vmrent.web.model.machine.MachineGaming;
import pl.vmrent.web.model.machine.MachineWorkstation;
import pl.vmrent.web.service.MachineService;
import pl.vmrent.web.service.RentService;

import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class MachineController implements Serializable
{
    @Inject
    private MachineService machineService;

    @Inject
    private RentService rentService;

    private Machine machine = new MachineGaming();

    public String initMachine()
    {
        machineService.findMachineById(machine.getId()).ifPresent(this::setMachine);
        return !rentService.isMachineAllocated(machine) ? null : "error";
    }

    public String submit()
    {
        machineService.saveMachine(machine);
        return "/dashboard/show-vms?faces-redirect=true";
    }

    public void onTypeChange(ValueChangeEvent event)
    {
        String type = (String) event.getNewValue();
        switch (type)
        {
            case "Gaming":
            {
                machine = new MachineGaming();
                break;
            }
            case "Workstation":
            {
                machine = new MachineWorkstation();
                break;
            }
        }
    }

    public boolean isUpdate()
    {
        return machineService.existsMachine(machine);
    }

    public Machine getMachine()
    {
        return machine;
    }

    public void setMachine(Machine machine)
    {
        this.machine = machine;
    }

    public MachineGaming getMachineGaming()
    {
        return machine instanceof MachineGaming ? (MachineGaming) machine : null;
    }

    public MachineWorkstation getMachineWorkstation()
    {
        return machine instanceof MachineWorkstation ? (MachineWorkstation) machine : null;
    }
}
