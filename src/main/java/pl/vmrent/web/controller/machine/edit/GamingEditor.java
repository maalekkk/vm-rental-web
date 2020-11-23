package pl.vmrent.web.controller.machine.edit;

import pl.vmrent.web.model.machine.MachineGaming;
import pl.vmrent.web.service.MachineService;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class GamingEditor extends MachineEditController<MachineGaming>
{
    @Inject
    public GamingEditor(MachineGaming machine, MachineService machineService)
    {
        super(machine, machineService);
    }
}
