package pl.vmrent.web.util;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.time.LocalDateTime;

@ApplicationScoped
public class DateTimeProvider implements Serializable
{
    public LocalDateTime now()
    {
        return LocalDateTime.now();
    }
}
