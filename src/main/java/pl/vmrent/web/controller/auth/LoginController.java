package pl.vmrent.web.controller.auth;

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
import javax.validation.constraints.NotBlank;

@Named
@RequestScoped
public class LoginController
{
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @Inject
    private SecurityContext securityContext;

    public String submit()
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        switch (authenticate(facesContext.getExternalContext()))
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
                return "/dashboard?faces-redirect=true";
            }
            case NOT_DONE:
                break;
        }
        return null;
    }

    private AuthenticationStatus authenticate(ExternalContext context)
    {
        return securityContext.authenticate(
                (HttpServletRequest) context.getRequest(),
                (HttpServletResponse) context.getResponse(),
                AuthenticationParameters.withParams()
                        .credential(new UsernamePasswordCredential(username, password))
        );
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
