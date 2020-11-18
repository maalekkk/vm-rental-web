package pl.vmrent.web.security;

import pl.vmrent.web.model.user.User;
import pl.vmrent.web.service.PasswordService;
import pl.vmrent.web.service.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import java.util.Optional;

@CustomFormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/auth/login",
                errorPage = "/auth/login",
                useForwardToLogin = false
        )
)
@ApplicationScoped
public class InMemoryIdentityStore implements IdentityStore
{
    @Inject
    private UserService userService;

    @Inject
    private PasswordService passwordService;

    @Override
    public CredentialValidationResult validate(Credential credential)
    {
        final UsernamePasswordCredential login = (UsernamePasswordCredential) credential;

        Optional<User> optionalUser = userService.findUserByUsername(login.getCaller());
        if (optionalUser.isPresent())
        {
            User user = optionalUser.get();
            if (passwordService.verify(login.getPasswordAsString(), user.getPassword()) && user.isEnabled())
            {
                return new CredentialValidationResult(user.getUsername(), user.getRoles());
            }
        }
        return CredentialValidationResult.NOT_VALIDATED_RESULT;
    }
}
