package pl.vmrent.web.util;

import javax.enterprise.context.RequestScoped;
import java.time.LocalDateTime;

@RequestScoped
public class DateTimeProvider
{
    public LocalDateTime now()
    {
        return LocalDateTime.now();
    }
}
