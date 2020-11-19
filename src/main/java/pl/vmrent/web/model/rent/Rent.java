package pl.vmrent.web.model.rent;

import pl.vmrent.web.model.Entity;
import pl.vmrent.web.model.machine.Machine;
import pl.vmrent.web.model.user.User;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.ZonedDateTime;

public class Rent extends Entity
{
    @Valid
    private Machine machine;

    @Valid
    private User user;

    @NotNull
    @PastOrPresent
    private ZonedDateTime rentDate;

    @PastOrPresent
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
}
