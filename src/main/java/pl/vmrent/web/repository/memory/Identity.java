package pl.vmrent.web.repository.memory;

import java.io.Serializable;

public interface Identity<ID extends Serializable> extends Serializable
{
    ID getId();

    void setId(ID id);
}
