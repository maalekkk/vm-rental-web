package pl.vmrent.web.repository.generator;

import java.io.Serializable;

public interface PrimaryKeyGenerator<ID extends Serializable>
{
    ID getId();
}
