package pl.vmrent.web.security;

import pl.vmrent.web.model.user.User;
import pl.vmrent.web.service.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import java.util.Optional;

@ApplicationScoped
public class InMemoryIdentityStore implements IdentityStore
{
    @Inject
    private UserService userService;

    @Override
    public CredentialValidationResult validate(Credential credential)
    {
        UsernamePasswordCredential login = (UsernamePasswordCredential) credential;

        Optional<User> optionalUser = userService.findUserByUsername(login.getCaller());
        if (optionalUser.isPresent())
        {
            User user = optionalUser.get();
            if (login.getPasswordAsString().equals(user.getPassword()) && user.isEnabled())
            {
                return new CredentialValidationResult(user.getUsername(), user.getRoles());
            }
        }
        return CredentialValidationResult.NOT_VALIDATED_RESULT;
    }
}
