package pl.vmrent.web.model;

import java.io.Serializable;

public interface Identity<ID extends Serializable> extends Serializable
{
    ID getId();
}
