package pl.vmrent.web.repository;

import pl.vmrent.web.model.Identity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface CrudRepository<T extends Identity<ID>, ID extends Serializable> extends Serializable
{
    <S extends T> S save(S entity);

    boolean existsById(ID id);

    Optional<T> findById(ID id);

    List<T> findAll();

    long count();

    void deleteById(ID id);

    void deleteAll();
}