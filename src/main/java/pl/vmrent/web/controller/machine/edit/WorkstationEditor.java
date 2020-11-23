package pl.vmrent.web.controller.machine.edit;

import pl.vmrent.web.model.machine.MachineWorkstation;
import pl.vmrent.web.service.MachineService;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class WorkstationEditor extends MachineEditController<MachineWorkstation>
{
    @Inject
    public WorkstationEditor(MachineWorkstation machine, MachineService machineService)
    {
        super(machine, machineService);
    }
}
