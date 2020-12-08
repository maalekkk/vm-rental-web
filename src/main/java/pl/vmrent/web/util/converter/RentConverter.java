package pl.vmrent.web.util.converter;

import pl.vmrent.web.model.rent.Rent;
import pl.vmrent.web.service.RentService;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@FacesConverter(forClass = Rent.class, managed = true)
public class RentConverter implements Converter<Rent>
{
    @Inject
    private RentService rentService;

    @Override
    public Rent getAsObject(FacesContext facesContext, UIComponent uiComponent, String s)
    {
        return rentService.findRentById(UUID.fromString(s)).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Rent rent)
    {
        return rent.getId() != null ? rent.getId().toString() : null;
    }
}
