package pl.vmrent.web.controller.machine;

import pl.vmrent.web.model.machine.MachineGaming;
import pl.vmrent.web.service.MachineService;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class MachineGamingController extends MachineController<MachineGaming>
{
    @Inject
    public MachineGamingController(MachineGaming machine, MachineService machineService)
    {
        super(machine, machineService);
    }
}
