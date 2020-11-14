package pl.vmrent.web.model.rent;

import pl.vmrent.web.model.machine.Machine;
import pl.vmrent.web.model.user.User;
import pl.vmrent.web.repository.Identity;

import java.math.BigInteger;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

public class Rent implements Identity<UUID>
{
    private UUID id;
    private Machine machine;
    private User user;
    private ZonedDateTime rentDate;
    private ZonedDateTime returnDate;
    private BigInteger price;

    public Rent()
    {
    }

    public Rent(UUID id, Machine machine, User user, ZonedDateTime rentDate, ZonedDateTime returnDate, BigInteger price)
    {
        this.id = id;
        this.machine = machine;
        this.user = user;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.price = price;
    }

    @Override
    public UUID getId()
    {
        return id;
    }

    @Override
    public void setId(UUID id)
    {
        this.id = id;
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

    public BigInteger getPrice()
    {
        return price;
    }

    public void setPrice(BigInteger price)
    {
        this.price = price;
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
