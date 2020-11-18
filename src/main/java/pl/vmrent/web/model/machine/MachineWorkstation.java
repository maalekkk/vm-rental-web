package pl.vmrent.web.model.machine;

public class MachineWorkstation extends Machine
{
    private int ssdSize;
    private int netCards;
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
