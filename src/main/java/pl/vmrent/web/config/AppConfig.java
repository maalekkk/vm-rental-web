package pl.vmrent.web.config;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;

@CustomFormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/auth/login.xhtml",
                errorPage = "/auth/login.xhtml",
                useForwardToLogin = false
        )
)
@FacesConfig
@ApplicationScoped
public class AppConfig
{
}
