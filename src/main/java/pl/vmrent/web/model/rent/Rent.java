package pl.vmrent.web.model.rent;

import pl.vmrent.web.model.Entity;
import pl.vmrent.web.model.machine.Machine;
import pl.vmrent.web.model.user.User;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Rent extends Entity
{
    @Valid
    @NotNull
    private Machine machine;

    @Valid
    @NotNull
    private User user;

    @Valid
    @NotNull
    private Period period;

    public Rent()
    {
        this.period = new Period();
    }

    public Rent(Machine machine, User user, Period period)
    {
        this.machine = machine;
        this.user = user;
        this.period = period;
    }

    public Machine getMachine()
    {
        return machine;
    }

    public void setMachine(Machine machine)
    {
        this.machine = machine;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Period getPeriod()
    {
        return period;
    }

    public void setPeriod(Period period)
    {
        this.period = period;
    }

    public static class SimpleRent extends Entity
    {
        @NotBlank
        private String machineName;

        @Valid
        @NotNull
        private Period period;

        public SimpleRent()
        {
        }

        public SimpleRent(String machineName, Period period)
        {
            this.machineName = machineName;
            this.period = period;
        }

        public String getMachineName()
        {
            return machineName;
        }

        public void setMachineName(String machineName)
        {
            this.machineName = machineName;
        }

        public Period getPeriod()
        {
            return period;
        }

        public void setPeriod(Period period)
        {
            this.period = period;
        }
    }
}
