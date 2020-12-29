package pl.vmrent.web.repository.generator;

import java.util.UUID;

public class UuidGenerator implements PrimaryKeyGenerator<UUID>
{
    @Override
    public UUID generateId()
    {
        return UUID.randomUUID();
    }
}
