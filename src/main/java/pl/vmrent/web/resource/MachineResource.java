package pl.vmrent.web.resource;

import pl.vmrent.web.model.machine.Machine;
import pl.vmrent.web.model.machine.MachineGaming;
import pl.vmrent.web.model.machine.MachineWorkstation;
import pl.vmrent.web.service.MachineService;

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
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
@Path("/machines")
public class MachineResource
{
    @Inject
    private MachineService machineService;

    @POST
    @Path("/gaming")
    public Response addMachineGaming(@NotNull @Valid MachineGaming machineGaming)
    {
        return addMachine(machineGaming);
    }

    @POST
    @Path("/workstation")
    public Response addMachineWorkstation(@NotNull @Valid MachineWorkstation machineWorkstation)
    {
        return addMachine(machineWorkstation);
    }

    @GET
    @Path("/{id}")
    public Response getMachineById(@PathParam("id") UUID machineId)
    {
        return machineService.findMachineById(machineId)
                .map(Response::ok)
                .orElseThrow(NotFoundException::new)
                .build();
    }

    @GET
    @Path("/machine")
    public Response getMachineByName(@QueryParam("machinename") String machineName)
    {
        return machineService.findMachineByName(machineName)
                .map(Response::ok)
                .orElseThrow(NotFoundException::new)
                .build();
    }

    @GET
    public List<Machine> getMachines()
    {
        return machineService.getAll();
    }

    @PUT
    @Path("/gaming/{id}")
    public Response updateMachineGaming(@PathParam("id") UUID machineId,
                                        @NotNull @Valid MachineGaming machineGaming)
    {
        return updateMachine(machineId, machineGaming);
    }

    @PUT
    @Path("/workstation/{id}")
    public Response updateMachineWorkstation(@PathParam("id") UUID machineId,
                                             @NotNull @Valid MachineWorkstation machineWorkstation)
    {
        return updateMachine(machineId, machineWorkstation);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteMachineById(@PathParam("id") UUID machineId)
    {
        return machineService.deleteMachineById(machineId)
                ? Response.ok().build()
                : Response.status(NOT_FOUND).build();
    }

    private Response addMachine(Machine machine)
    {
        return Optional.ofNullable(machine.getId())
                .map(m -> Response.status(BAD_REQUEST))
                .orElse(Response.ok(machineService.saveMachine(machine)))
                .build();
    }

    private Response updateMachine(UUID machineId, Machine machine)
    {
        return machineService.findMachineById(machineId)
                .map(m ->
                {
                    machine.setId(m.getId());
                    return machineService.saveMachine(machine);
                })
                .map(Response::ok)
                .orElseThrow(NotFoundException::new)
                .build();
    }
}
