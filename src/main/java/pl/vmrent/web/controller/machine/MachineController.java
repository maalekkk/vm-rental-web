package pl.vmrent.web.controller.machine;

import pl.vmrent.web.model.machine.Machine;
import pl.vmrent.web.model.machine.MachineGaming;
import pl.vmrent.web.model.machine.MachineWorkstation;
import pl.vmrent.web.service.MachineService;
import pl.vmrent.web.validator.unique.machinename.UniqueMachineName;

import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Named
@ViewScoped
public class MachineController implements Serializable
{
    @Inject
    private MachineService machineService;

    @UniqueMachineName
    @Size(min = 3, max = 20)
    private String machineName;

    private Machine machine = new MachineGaming();

    public String submit()
    {
        if (machineName != null)
        {
            machine.setName(machineName);
        }
        machineService.saveMachine(machine);
        return "/dashboard/show_vms?faces-redirect=true";
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
        return machine != null && machine.getId() != null;
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

    public String getMachineName()
    {
        return machineName;
    }

    public void setMachineName(String machineName)
    {
        this.machineName = machineName;
    }
}
