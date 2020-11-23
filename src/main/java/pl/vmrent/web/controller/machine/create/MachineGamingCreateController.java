package pl.vmrent.web.controller.machine.create;

import pl.vmrent.web.model.machine.MachineGaming;
import pl.vmrent.web.service.MachineService;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class MachineGamingCreateController extends MachineCreateController<MachineGaming>
{
    @Inject
    public MachineGamingCreateController(MachineGaming machine, MachineService machineService)
    {
        super(machine, machineService);
    }
}
