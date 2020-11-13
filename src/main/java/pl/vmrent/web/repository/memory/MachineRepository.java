package pl.vmrent.web.repository.memory;

import pl.vmrent.web.model.machine.Machine;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
public class MachineRepository extends AbstractCrudRepository<Machine, UUID>
{

}
