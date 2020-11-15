package pl.vmrent.web.model.machine;

import java.util.UUID;

public abstract class MachinePremium extends Machine
{
    private int ssdSize;

    public MachinePremium()
    {
    }

    public MachinePremium(UUID id, int basicPrice, String system, int cores, int ramSize, int hddSize, int ssdSize)
    {
        super(id, basicPrice, system, cores, ramSize, hddSize);
        this.ssdSize = ssdSize;
    }

    public int getSsdSize()
    {
        return ssdSize;
    }

    public void setSsdSize(int ssdSize)
    {
        this.ssdSize = ssdSize;
    }

}
