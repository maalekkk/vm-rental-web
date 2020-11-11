package pl.vmrent.web.model.machine;

import java.util.UUID;

public class MachineBasic extends Machine
{
    public MachineBasic()
    {
    }

    public MachineBasic(UUID id, int basicPrice, String system, int cores, int ramSize, int hddSize)
    {
        super(id, basicPrice, system, cores, ramSize, hddSize);
    }
}
