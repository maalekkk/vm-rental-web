package pl.vmrent.web.repository;

import pl.vmrent.web.model.Identity;

import javax.persistence.EntityNotFoundException;
import java.io.Serializable;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class AbstractCrudRepository<T extends Identity<ID>, ID extends Serializable> implements CrudRepository<T, ID>
{
    private final Collection<T> elements = Collections.synchronizedList(new ArrayList<>());

    @Override
    public <S extends T> S save(S entity)
    {
        if (entity == null)
        {
            throw new IllegalArgumentException();
        }

        synchronized (elements)
        {
            List<T> list = (List<T>) elements;
            int index = list.indexOf(entity);
            if (index == -1)
            {
                elements.add(entity);
            }
            else
            {
                list.set(index, entity);
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
        if (id == null)
        {
            throw new IllegalArgumentException();
        }
        return elements.stream().filter(element -> element.getId().equals(id)).findFirst();
    }

    public Optional<T> findByUniquePredicate(Predicate<T> predicate)
    {
        return elements.stream().filter(predicate).findFirst();
    }

    public Iterable<T> findByPredicate(Predicate<T> predicate)
    {
        return elements.stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public Iterable<T> findAll()
    {
        return Collections.unmodifiableCollection(elements);
    }

    @Override
    public long count()
    {
        return elements.size();
    }

    @Override
    public void delete(T entity)
    {
        if (entity == null)
        {
            throw new IllegalArgumentException();
        }
        synchronized (elements)
        {
            elements.remove(Optional.of(entity).orElseThrow(EntityNotFoundException::new));
        }
    }

    @Override
    public void deleteById(ID id)
    {
        synchronized (elements)
        {
            elements.remove(findById(id).orElseThrow(EntityNotFoundException::new));
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
