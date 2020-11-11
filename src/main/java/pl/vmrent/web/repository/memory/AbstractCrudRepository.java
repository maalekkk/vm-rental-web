package pl.vmrent.web.repository.memory;

import pl.vmrent.web.exceptions.NullArgumentException;

import javax.persistence.EntityNotFoundException;
import java.io.Serializable;
import java.util.*;

public abstract class AbstractCrudRepository<T extends Identity<ID>, ID extends Serializable> implements CrudRepository<T, ID>
{
    protected Collection<T> elements = new ArrayList<>();

    @Override
    public <S extends T> S save(S entity)
    {
        if (entity == null)
        {
            throw new NullArgumentException();
        }

        if (!elements.contains(entity))
        {
            elements.add(entity);
        }
        else
        {
            List<T> list = (List<T>) elements;
            list.set(list.indexOf(entity), entity);
        }
        return entity;
    }

    @Override
    public boolean existsById(ID id)
    {
        if (id == null)
        {
            throw new NullArgumentException();
        }
        return findById(id).isPresent();
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
        if (id == null)
        {
            throw new NullArgumentException();
        }
        elements.remove(findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public void deleteAll()
    {
        elements.clear();
    }
}
