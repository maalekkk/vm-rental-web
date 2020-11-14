package pl.vmrent.web.model.machine;

import pl.vmrent.web.repository.Identity;

import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

public abstract class Machine implements Identity<UUID>
{
    private UUID id;
    private int basicPrice;
    private String system;
    private int cores;
    private int ramSize;
    private int hddSize;

    public Machine()
    {
    }

    public Machine(UUID id, int basicPrice, String system, int cores, int ramSize, int hddSize)
    {
        this.id = id;
        this.basicPrice = basicPrice;
        this.system = system;
        this.cores = cores;
        this.ramSize = ramSize;
        this.hddSize = hddSize;
    }

    @Override
    public UUID getId()
    {
        return id;
    }

    public void setId(UUID id)
    {
        this.id = id;
    }

    public int getBasicPrice()
    {
        return basicPrice;
    }

    public void setBasicPrice(int basicPrice)
    {
        this.basicPrice = basicPrice;
    }

    public String getSystem()
    {
        return system;
    }

    public void setSystem(String system)
    {
        this.system = system;
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

    @Override
    public String toString()
    {
        return new StringJoiner(" | ", "", "")
                .add("id: " + id)
                .add("basicPrice: " + basicPrice)
                .add("system: '" + system + "'")
                .add("cores: " + cores)
                .add("ramSize: " + ramSize)
                .add("hddSize: " + hddSize)
                .toString();
    }
}
