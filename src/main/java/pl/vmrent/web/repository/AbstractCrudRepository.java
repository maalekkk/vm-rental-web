package pl.vmrent.web.repository;

import pl.vmrent.web.model.Identity;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class AbstractCrudRepository<T extends Identity<ID>, ID extends Serializable> implements CrudRepository<T, ID>
{
    private final List<T> elements = Collections.synchronizedList(new ArrayList<>());

    @Override
    public <S extends T> S save(@NotNull S entity)
    {
        synchronized (elements)
        {
            int index = elements.indexOf(entity);
            if (index == -1)
            {
                elements.add(entity);
            }
            else
            {
                elements.set(index, entity);
            }
            return entity;
        }
    }

    @Override
    public boolean existsById(ID id)
    {
        return elements.stream().anyMatch(e -> e.getId().equals(id));
    }

    @Override
    public Optional<T> findById(ID id)
    {
        return findAll().stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    public Optional<T> findByUniquePredicate(Predicate<T> predicate)
    {
        return findAll().stream().filter(predicate).findFirst();
    }

    public List<T> findByPredicate(Predicate<T> predicate)
    {
        return Collections.unmodifiableList(elements.stream().filter(predicate).collect(Collectors.toList()));
    }

    @Override
    public List<T> findAll()
    {
        return Collections.unmodifiableList(elements);
    }

    @Override
    public long count()
    {
        return elements.size();
    }

    @Override
    public boolean delete(@NotNull T entity)
    {
        synchronized (elements)
        {
            return elements.remove(entity);
        }
    }

    @Override
    public void deleteAll()
    {
        synchronized (elements)
        {
            elements.clear();
        }
    }
}
