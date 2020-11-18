package pl.vmrent.web.service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

@RequestScoped
public class PasswordService
{
    @Inject
    private Pbkdf2PasswordHash hasher;

    public String hash(String password)
    {
        return hasher.generate(password.toCharArray());
    }

    public boolean verify(String password, String hashedPassword)
    {
        return hasher.verify(password.toCharArray(), hashedPassword);
    }
}
