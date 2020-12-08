package pl.vmrent.web.util.converter;

import pl.vmrent.web.model.machine.Machine;
import pl.vmrent.web.service.MachineService;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@FacesConverter(forClass = Machine.class, managed = true)
public class MachineConverter implements Converter<Machine>
{
    @Inject
    private MachineService machineService;

    @Override
    public Machine getAsObject(FacesContext facesContext, UIComponent uiComponent, String s)
    {
        return machineService.findMachineById(UUID.fromString(s)).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Machine machine)
    {
        return machine.getId() != null ? machine.getId().toString() : null;
    }
}
