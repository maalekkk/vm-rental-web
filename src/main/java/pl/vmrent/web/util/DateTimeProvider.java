package pl.vmrent.web.util;

import javax.enterprise.context.RequestScoped;
import java.time.ZonedDateTime;

@RequestScoped
public class DateTimeProvider
{
    public ZonedDateTime now()
    {
        return ZonedDateTime.now();
    }
}
