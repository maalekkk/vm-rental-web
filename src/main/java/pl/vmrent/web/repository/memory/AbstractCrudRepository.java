package pl.vmrent.web.repository.memory;

import pl.vmrent.web.exceptions.NullArgumentException;

import javax.persistence.EntityNotFoundException;
import java.io.Serializable;
import java.util.*;

public abstract class AbstractCrudRepository<T extends Identity<ID>, ID extends Serializable> implements CrudRepository<T, ID>
{
    protected Collection<T> elements;

    public AbstractCrudRepository()
    {
        elements = Collections.synchronizedList(new ArrayList<>());
    }

    @Override
    public <S extends T> S save(S entity)
    {
        if (entity == null)
        {
            throw new NullArgumentException();
        }

        int index = ((List<T>) elements).indexOf(entity);
        if (index == -1)
        {
            elements.add(entity);
        }
        else
        {
            ((List<T>) elements).set(index, entity);
        }
        return entity;
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
            throw new NullArgumentException();
        }
        return elements.stream().findFirst().filter(element -> element.getId().equals(id));
    }

    @Override
    public T getOne(ID id)
    {
        return findById(id).orElseThrow(EntityNotFoundException::new);
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
            throw new NullArgumentException();
        }
        elements.remove(entity);
    }

    @Override
    public void deleteById(ID id)
    {
        elements.remove(getOne(id));
    }

    @Override
    public void deleteAll()
    {
        elements.clear();
    }
}
