package pl.vmrent.web.controller.machine;

import pl.vmrent.web.model.machine.Machine;
import pl.vmrent.web.model.machine.MachineGaming;
import pl.vmrent.web.model.machine.MachineWorkstation;
import pl.vmrent.web.service.MachineService;

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

    private Machine machine = new MachineGaming();

    public String submit()
    {
        machineService.saveMachine(machine);
        return "/dashboard/show_vms?faces-redirect=true";
    }

    public void onTypeChange(ValueChangeEvent event)
    {
        String type = (String) event.getNewValue();
        switch (type)
        {
            case "MachineGaming":
            {
                machine = new MachineGaming();
                break;
            }
            case "MachineWorkstation":
            {
                machine = new MachineWorkstation();
                break;
            }
        }
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
