package pl.vmrent.web.repository.memory;

import java.io.Serializable;
import java.util.Optional;

public interface CrudRepository<T extends Identity<ID>, ID extends Serializable> extends Serializable
{
    <S extends T> S save(S entity);

    boolean existsById(ID id);

    Optional<T> findById(ID id);

    T getOne(ID id);

    Iterable<T> findAll();

    long count();

    void delete(T entity);

    void deleteById(ID id);

    void deleteAll();
}