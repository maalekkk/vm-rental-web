package pl.vmrent.web.model.machine;

import pl.vmrent.web.model.Identity;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.UUID;

public abstract class Machine implements Identity<UUID>
{
    private final UUID id = UUID.randomUUID();

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

    @Override
    public UUID getId()
    {
        return id;
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

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        Machine machine = (Machine) o;
        return id.equals(machine.id);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id);
    }
}
