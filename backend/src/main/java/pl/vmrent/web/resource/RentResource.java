package pl.vmrent.web.resource;

import pl.vmrent.web.model.rent.Rent;
import pl.vmrent.web.model.rent.Rent.SimpleRent;
import pl.vmrent.web.service.MachineService;
import pl.vmrent.web.service.RentService;
import pl.vmrent.web.service.UserService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static pl.vmrent.web.model.user.Role.CLIENT;

@Path("/rents")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class RentResource
{
    @Inject
    private RentService rentService;

    @Inject
    private UserService userService;

    @Inject
    private MachineService machineService;

    @POST
    @Path("/create")
    @RolesAllowed({CLIENT})
    public Response createRent(@NotNull SimpleRent simpleRent)
    {
        return machineService.findMachineByName(simpleRent.getMachineName())
                .map(machine -> new Rent(machine, userService.getCurrentUser(), simpleRent.getPeriod()))
                .map(rentService::saveRent)
                .map(Response::ok)
                .orElseThrow(BadRequestException::new)
                .build();
    }

    @GET
    @Path("/me")
    @RolesAllowed({CLIENT})
    public List<Rent> currentUserRents()
    {
        return rentService.findRentsByUser(userService.getCurrentUser());
    }
}
