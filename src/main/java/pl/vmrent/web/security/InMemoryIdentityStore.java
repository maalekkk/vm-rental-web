package pl.vmrent.web.security;

import pl.vmrent.web.service.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;

import static javax.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;
import static javax.security.enterprise.identitystore.CredentialValidationResult.NOT_VALIDATED_RESULT;

@CustomFormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/auth/login.xhtml",
                errorPage = "/auth/login.xhtml",
                useForwardToLogin = false
        )
)
@ApplicationScoped
public class InMemoryIdentityStore implements IdentityStore
{
    @Inject
    private UserService userService;

    @Override
    public CredentialValidationResult validate(Credential credential)
    {
        if (credential instanceof UsernamePasswordCredential)
        {
            final UsernamePasswordCredential cred = (UsernamePasswordCredential) credential;
            return userService.findUserByUsername(cred.getCaller())
                    .filter(user -> cred.getPassword().compareTo(user.getPassword()) && user.isEnabled())
                    .map(user -> new CredentialValidationResult(user.getUsername(), user.getRolesAsString()))
                    .orElse(INVALID_RESULT);
        }
        return NOT_VALIDATED_RESULT;
    }
}