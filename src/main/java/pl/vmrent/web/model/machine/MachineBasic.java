package pl.vmrent.web.model.machine;

public class MachineBasic extends Machine
{
    public MachineBasic()
    {
    }

    public MachineBasic(Long id, int basicPrice, String system, int cores, int ramSize, int hddSize)
    {
        super(id, basicPrice, system, cores, ramSize, hddSize);
    }
}
