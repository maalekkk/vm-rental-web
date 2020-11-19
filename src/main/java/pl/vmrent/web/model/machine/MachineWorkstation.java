package pl.vmrent.web.model.machine;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MachineWorkstation extends Machine
{
    @Min(1)
    private int ssdSize;

    @Min(0)
    @Max(10)
    private int netCards;

    @NotNull
    private boolean raidSupport;

    public MachineWorkstation()
    {
    }

    public MachineWorkstation(String name, int cores, int ramSize, int hddSize, int ssdSize, int netCards, boolean raidSupport)
    {
        super(name, cores, ramSize, hddSize);
        this.ssdSize = ssdSize;
        this.netCards = netCards;
        this.raidSupport = raidSupport;
    }

    public int getSsdSize()
    {
        return ssdSize;
    }

    public void setSsdSize(int ssdSize)
    {
        this.ssdSize = ssdSize;
    }

    public int getNetCards()
    {
        return netCards;
    }

    public void setNetCards(int netCards)
    {
        this.netCards = netCards;
    }

    public boolean isRaidSupport()
    {
        return raidSupport;
    }

    public void setRaidSupport(boolean raidSupport)
    {
        this.raidSupport = raidSupport;
    }
}
