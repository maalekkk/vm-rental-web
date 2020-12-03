package pl.vmrent.web.repository;

import pl.vmrent.web.model.Identity;
import pl.vmrent.web.repository.generator.PrimaryKeyGenerator;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class InMemoryRepository<T extends Identity<ID>, ID extends Serializable> implements Repository<T, ID>
{
    private final PrimaryKeyGenerator<ID> generator;
    private final List<T> elements = Collections.synchronizedList(new ArrayList<>());

    public InMemoryRepository(PrimaryKeyGenerator<ID> generator)
    {
        this.generator = generator;
    }

    @Override
    public <S extends T> S save(@NotNull S entity)
    {
        if (entity.getId() == null)
        {
            entity.setId(generator.getId());
            elements.add(entity);
        }
        else
        {
            int index = elements.indexOf(entity);
            elements.set(index, entity);
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
        return elements.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    public Optional<T> findByUniquePredicate(Predicate<T> predicate)
    {
        return elements.stream().filter(predicate).findFirst();
    }

    public List<T> findByPredicate(Predicate<T> predicate)
    {
        return elements.stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public List<T> findAll()
    {
        return new ArrayList<>(elements);
    }

    @Override
    public long count()
    {
        return elements.size();
    }

    @Override
    public void delete(@NotNull T entity)
    {
        elements.remove(entity);
    }

    @Override
    public void deleteAll()
    {
        elements.clear();
    }
}
