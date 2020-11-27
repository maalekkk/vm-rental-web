package pl.vmrent.web.repository.generator;

import java.util.UUID;

public class UuidGenerator implements PrimaryKeyGenerator<UUID>
{
    @Override
    public UUID getId()
    {
        return UUID.randomUUID();
    }
}
