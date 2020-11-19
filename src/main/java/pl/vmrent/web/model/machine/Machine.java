package pl.vmrent.web.model.machine;

import pl.vmrent.web.model.Entity;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public abstract class Machine extends Entity
{
    @Size(min = 3, max = 20)
    private String name;

    @Min(1)
    private int cores;

    @Min(1)
    private int ramSize;

    @Min(1)
    private int hddSize;

    public Machine()
    {
    }

    public Machine(String name, int cores, int ramSize, int hddSize)
    {
        this.name = name;
        this.cores = cores;
        this.ramSize = ramSize;
        this.hddSize = hddSize;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getCores()
    {
        return cores;
    }

    public void setCores(int cores)
    {
        this.cores = cores;
    }

    public int getRamSize()
    {
        return ramSize;
    }

    public void setRamSize(int ramSize)
    {
        this.ramSize = ramSize;
    }

    public int getHddSize()
    {
        return hddSize;
    }

    public void setHddSize(int hddSize)
    {
        this.hddSize = hddSize;
    }
}
