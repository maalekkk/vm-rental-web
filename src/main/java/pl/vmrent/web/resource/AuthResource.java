package pl.vmrent.web.resource;

import pl.vmrent.web.model.user.User;
import pl.vmrent.web.security.jwt.TokenProvider;
import pl.vmrent.web.service.UserService;

import javax.inject.Inject;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.IdentityStore;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

import static javax.security.enterprise.identitystore.CredentialValidationResult.Status.VALID;
import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;
import static pl.vmrent.web.security.jwt.JwtAuthenticationMechanism.extractToken;

@Produces(TEXT_PLAIN)
@Path("/auth")
public class AuthResource
{
    public static final MediaType APPLICATION_JWT = new MediaType("application", "jwt");

    @Inject
    private IdentityStore identityStore;

    @Inject
    private UserService userService;

    @Inject
    private TokenProvider tokenProvider;

    @POST
    @Path("/login")
    @Consumes(APPLICATION_FORM_URLENCODED)
    public Response login(@FormParam("username") String username, @FormParam("password") String password)
    {
        return Optional.of(identityStore.validate(new UsernamePasswordCredential(username, password)))
                .filter(result -> result.getStatus() == VALID)
                .map(result -> tokenProvider.createToken(result.getCallerPrincipal().getName(), result.getCallerGroups()))
                .map(token -> Response.ok(token).type(APPLICATION_JWT))
                .orElseGet(() -> Response.status(UNAUTHORIZED))
                .build();
    }

    @GET
    @Path("/refresh")
    public Response refresh(@Context HttpServletRequest request)
    {
        return extractToken(request)
                .filter(tokenProvider::isValid)
                .map(tokenProvider::extractCredential)
                .filter(cred -> userService.findUserByUsername(cred.getUsername())
                        .filter(User::isEnabled)
                        .isPresent())
                .map(tokenProvider::createToken)
                .map(token -> Response.ok(token).type(APPLICATION_JWT))
                .orElseGet(() -> Response.status(UNAUTHORIZED))
                .build();
    }
}
