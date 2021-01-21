package pl.vmrent.web.repository;

import pl.vmrent.web.model.Identity;

import java.io.Serializable;
import java.util.List;

public interface PaginationRepository<T extends Identity<ID>, ID extends Serializable> extends Repository<T, ID>
{
    List<T> findAll(int page, int size);
}
