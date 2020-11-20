package pl.vmrent.web.model.rent;

import pl.vmrent.web.validator.period.PeriodConstraint;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.time.ZonedDateTime;

@PeriodConstraint
public class Period implements Serializable
{
    @NotNull
    @PastOrPresent
    private ZonedDateTime startDate;

    @NotNull
    private ZonedDateTime endDate;

    public ZonedDateTime getStartDate()
    {
        return startDate;
    }

    public void setStartDate(ZonedDateTime startDate)
    {
        this.startDate = startDate;
    }

    public ZonedDateTime getEndDate()
    {
        return endDate;
    }

    public void setEndDate(ZonedDateTime endDate)
    {
        this.endDate = endDate;
    }
}
