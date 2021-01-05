package pl.vmrent.web.resource;

import pl.vmrent.web.model.user.User;
import pl.vmrent.web.service.UserService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

@Path("/users")
public class UserResource
{
    @Inject
    private UserService userService;

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response addUser(@NotNull @Valid User user)
    {
        return Optional.ofNullable(user.getId())
                .map(u -> Response.status(BAD_REQUEST))
                .orElse(Response.ok(userService.saveUser(user)))
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(APPLICATION_JSON)
    public Response getUserById(@PathParam("id") UUID userId)
    {
        return userService.findUserById(userId)
                .map(Response::ok)
                .orElseThrow(NotFoundException::new)
                .build();
    }

    @GET
    @Path("/user")
    @Produces(APPLICATION_JSON)
    public Response getUserByUsername(@QueryParam("username") String username)
    {
        return userService.findUserByUsername(username)
                .map(Response::ok)
                .orElseThrow(NotFoundException::new)
                .build();
    }

    @GET
    @Produces(APPLICATION_JSON)
    public List<User> getUsers()
    {
        return userService.getAll();
    }

    @PUT
    @Path("/{id}")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response updateUser(@PathParam("id") UUID userId, @NotNull @Valid User user)
    {
        return userService.findUserById(userId)
                .map(u ->
                {
                    user.setId(u.getId());
                    return userService.saveUser(user);
                })
                .map(Response::ok)
                .orElseThrow(NotFoundException::new)
                .build();
    }
}
