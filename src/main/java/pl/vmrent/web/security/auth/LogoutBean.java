package pl.vmrent.web.security.auth;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class LogoutBean
{
    public void submit() throws IOException
    {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.invalidateSession();
        context.redirect(context.getRequestContextPath() + "/auth/login");
    }
}
