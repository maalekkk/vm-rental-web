package pl.vmrent.web.model.rent;

import pl.vmrent.web.model.Identity;
import pl.vmrent.web.model.machine.Machine;
import pl.vmrent.web.model.user.User;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

public class Rent implements Identity<UUID>
{
    private final UUID id = UUID.randomUUID();
    private Machine machine;
    private User user;
    private ZonedDateTime rentDate;
    private ZonedDateTime returnDate;

    public Rent()
    {
    }

    public Rent(Machine machine, User user, ZonedDateTime rentDate, ZonedDateTime returnDate)
    {
        this.machine = machine;
        this.user = user;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
    }

    @Override
    public UUID getId()
    {
        return id;
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

    public ZonedDateTime getRentDate()
    {
        return rentDate;
    }

    public void setRentDate(ZonedDateTime rentDate)
    {
        this.rentDate = rentDate;
    }

    public ZonedDateTime getReturnDate()
    {
        return returnDate;
    }

    public void setReturnDate(ZonedDateTime returnDate)
    {
        this.returnDate = returnDate;
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
        Rent rent = (Rent) o;
        return id.equals(rent.id);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id);
    }
}
