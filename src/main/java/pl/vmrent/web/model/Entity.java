package pl.vmrent.web.model;

import java.util.Objects;
import java.util.UUID;

public abstract class Entity implements Identity<UUID>
{
    private UUID id;

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

    public void setIdFromString(String s)
    {
        this.id = UUID.fromString(s);
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
