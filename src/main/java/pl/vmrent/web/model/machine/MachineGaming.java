package pl.vmrent.web.model.machine;

public class MachineGaming extends Machine
{
    private int gpuPower;
    private int gpuVram;

    public MachineGaming()
    {
    }

    public MachineGaming(String name, int cores, int ramSize, int hddSize, int gpuPower, int gpuVram)
    {
        super(name, cores, ramSize, hddSize);
        this.gpuPower = gpuPower;
        this.gpuVram = gpuVram;
    }

    public int getGpuPower()
    {
        return gpuPower;
    }

    public void setGpuPower(int gpuPower)
    {
        this.gpuPower = gpuPower;
    }

    public int getGpuVram()
    {
        return gpuVram;
    }

    public void setGpuVram(int gpuVram)
    {
        this.gpuVram = gpuVram;
    }
}
