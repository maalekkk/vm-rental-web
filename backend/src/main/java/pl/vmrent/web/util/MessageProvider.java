package pl.vmrent.web.util;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ResourceBundle;

@ApplicationScoped
public class MessageProvider implements Serializable
{
    @Inject
    private FacesContext context;

    public String getMessage(String bundle, String key)
    {
        return ResourceBundle.getBundle(bundle, context.getViewRoot().getLocale()).getString(key);
    }

    public void addMessage(String location, String msg, FacesMessage.Severity type)
    {
        FacesMessage message = new FacesMessage(type, msg, null);
        context.addMessage(location, message);
    }
}
