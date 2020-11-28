package pl.vmrent.web.util;

import pl.vmrent.web.model.user.User;
import pl.vmrent.web.service.UserService;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import java.util.UUID;

@FacesConverter(forClass = User.class, managed = true)
public class UserConverter implements Converter<User>
{
    @Inject
    private UserService userService;

    @Override
    public User getAsObject(FacesContext facesContext, UIComponent uiComponent, String s)
    {
        return userService.findUserById(UUID.fromString(s)).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, User user)
    {
        return user.getId() != null ? user.getId().toString() : null;
    }
}
