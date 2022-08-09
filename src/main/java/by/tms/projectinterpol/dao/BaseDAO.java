package by.tms.projectinterpol.dao;

import java.util.List;
import java.util.Optional;

public interface BaseDAO<E> {

    long save(E entity);

    void update(E entity);

    void delete(E entity);

    List<E> findAll();

    Optional<E> findById(long id);
}
