package pl.vmrent.web.controller.machine.create;

import pl.vmrent.web.model.machine.MachineWorkstation;
import pl.vmrent.web.service.MachineService;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class MachineWorkstationCreateController extends MachineCreateController<MachineWorkstation>
{
    @Inject
    public MachineWorkstationCreateController(MachineWorkstation machine, MachineService machineService)
    {
        super(machine, machineService);
    }
}
