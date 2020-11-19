package pl.vmrent.web.model;

import java.util.Objects;
import java.util.UUID;

public abstract class Entity implements Identity<UUID>
{
    private final UUID id = UUID.randomUUID();

    @Override
    public UUID getId()
    {
        return id;
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
        Entity entity = (Entity) o;
        return id.equals(entity.id);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id);
    }
}
