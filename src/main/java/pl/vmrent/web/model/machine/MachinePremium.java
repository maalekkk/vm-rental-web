package pl.vmrent.web.model.machine;

import java.util.StringJoiner;

public abstract class MachinePremium extends Machine
{
    private int ssdSize;

    public MachinePremium()
    {
    }

    public MachinePremium(Long id, int basicPrice, String system, int cores, int ramSize, int hddSize, int ssdSize)
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

    @Override
    public String toString()
    {
        return new StringJoiner(" | ", "", "")
                .add(super.toString())
                .add("ssdSize: " + ssdSize)
                .toString();
    }
}
