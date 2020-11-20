package pl.vmrent.web.repository;

import pl.vmrent.web.model.machine.Machine;
import pl.vmrent.web.model.machine.MachineGaming;
import pl.vmrent.web.model.machine.MachineWorkstation;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
public class MachineRepository extends AbstractCrudRepository<Machine, UUID>
{
    @PostConstruct
    private void init()
    {
        MachineGaming mg1 = new MachineGaming("Predator", 8, 16384, 4096, 15, 8192);
        MachineGaming mg2 = new MachineGaming("G4mer", 4, 8192, 2048, 8, 4096);
        MachineWorkstation mw1 = new MachineWorkstation("Developex", 16, 32768, 8192, 1024, 3, true);
        MachineWorkstation mw2 = new MachineWorkstation("W0rker", 8, 16384, 4096, 512, 2, true);
        save(mg1);
        save(mg2);
        save(mw1);
        save(mw2);
    }
}
