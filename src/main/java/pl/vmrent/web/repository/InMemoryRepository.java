package pl.vmrent.web.repository;

import pl.vmrent.web.model.Identity;
import pl.vmrent.web.repository.generator.PrimaryKeyGenerator;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class InMemoryRepository<T extends Identity<ID>, ID extends Serializable> implements PaginationRepository<T, ID>
{
    private final PrimaryKeyGenerator<ID> generator;
    private final List<T> elements = new CopyOnWriteArrayList<>();

    public InMemoryRepository(PrimaryKeyGenerator<ID> generator)
    {
        this.generator = generator;
    }

    @Override
    public <S extends T> S save(@NotNull S entity)
    {
        if (entity.getId() == null)
        {
            entity.setId(generator.generateId());
            elements.add(entity);
            return entity;
        }
        if (elements.contains(entity))
        {
            int index = elements.indexOf(entity);
            elements.set(index, entity);
            return entity;
        }
        throw new IllegalStateException("Invalid data!");
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

    public List<T> findByPredicate(Predicate<T> predicate, int page, int size)
    {
        return paginate(findByPredicate(predicate).stream(), page, size).collect(Collectors.toList());
    }

    @Override
    public List<T> findAll()
    {
        return new ArrayList<>(elements);
    }

    @Override
    public List<T> findAll(int page, int size)
    {
        return paginate(findAll().stream(), page, size).collect(Collectors.toList());
    }

    @Override
    public long count()
    {
        return elements.size();
    }

    @Override
    public boolean delete(@NotNull T entity)
    {
        return elements.remove(entity);
    }

    @Override
    public boolean deleteById(ID id)
    {
        return findById(id).map(elements::remove).orElse(false);
    }

    @Override
    public void deleteAll()
    {
        elements.clear();
    }

    private Stream<T> paginate(Stream<T> stream, int page, int size)
    {
        return stream.skip((long) (page - 1) * size).limit(size);
    }
}
