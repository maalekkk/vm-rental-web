package pl.vmrent.web.model.machine;

import java.util.StringJoiner;
import java.util.UUID;

public class MachineProfessional extends MachinePremium
{
    private int gpuPower;

    public MachineProfessional()
    {
    }

    public MachineProfessional(UUID id, int basicPrice, String system, int cores, int ramSize, int hddSize, int ssdSize, int gpuPower)
    {
        super(id, basicPrice, system, cores, ramSize, hddSize, ssdSize);
        this.gpuPower = gpuPower;
    }

    public int getGpuPower()
    {
        return gpuPower;
    }

    public void setGpuPower(int gpuPower)
    {
        this.gpuPower = gpuPower;
    }

    @Override
    public String toString()
    {
        return new StringJoiner(" | ", "", "")
                .add(super.toString())
                .add("gpuPower: " + gpuPower)
                .toString();
    }
}
