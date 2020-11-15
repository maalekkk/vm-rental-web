package pl.vmrent.web.security.auth;

import pl.vmrent.web.model.user.User;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Named
@RequestScoped
public class LoginBean
{
    @Inject
    private SecurityContext securityContext;

    @Inject
    private ExternalContext externalContext;

    @Inject
    private FacesContext facesContext;

    private User user = new User();

    public void submit() throws IOException
    {
        switch (continueAuth())
        {
            case SEND_CONTINUE:
            {
                facesContext.responseComplete();
                break;
            }
            case SEND_FAILURE:
            {
                facesContext.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed", null));
                break;
            }
            case SUCCESS:
            {
                facesContext.addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Login succeed", null));
                externalContext.redirect(externalContext.getRequestContextPath() + "/dashboard/");
                break;
            }
            case NOT_DONE:
        }
    }

    private AuthenticationStatus continueAuth()
    {
        return securityContext.authenticate(
                (HttpServletRequest) externalContext.getRequest(),
                (HttpServletResponse) externalContext.getResponse(),
                AuthenticationParameters.withParams()
                        .credential(new UsernamePasswordCredential(user.getUsername(), user.getPassword()))
        );
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}
