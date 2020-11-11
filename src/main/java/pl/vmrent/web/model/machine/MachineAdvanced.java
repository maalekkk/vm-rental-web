package pl.vmrent.web.model.machine;

import java.util.UUID;

public class MachineAdvanced extends MachinePremium
{
    public MachineAdvanced()
    {
    }

    public MachineAdvanced(UUID id, int basicPrice, String system, int cores, int ramSize, int hddSize, int ssdSize)
    {
        super(id, basicPrice, system, cores, ramSize, hddSize, ssdSize);
    }
}
