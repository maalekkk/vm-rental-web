package pl.vmrent.web.repository;

import pl.vmrent.web.model.Identity;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface Repository<T extends Identity<ID>, ID extends Serializable> extends Serializable
{
    <S extends T> S save(@NotNull S entity);

    boolean existsById(ID id);

    Optional<T> findById(ID id);

    List<T> findAll();

    long count();

    void delete(@NotNull T entity);

    void deleteAll();
}