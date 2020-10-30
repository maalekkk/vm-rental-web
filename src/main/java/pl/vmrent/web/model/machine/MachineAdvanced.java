package pl.vmrent.web.model.machine;

public class MachineAdvanced extends MachinePremium
{
    public MachineAdvanced()
    {
    }

    public MachineAdvanced(Long id, int basicPrice, String system, int cores, int ramSize, int hddSize, int ssdSize)
    {
        super(id, basicPrice, system, cores, ramSize, hddSize, ssdSize);
    }
}
