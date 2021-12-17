package com.bsu.project.service;

import com.bsu.project.repository.RepositoryException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Gulshirin Berdiyeva
 */
public abstract class AbstractService<T> implements Service<T> {
    private final JpaRepository<T, Long> repository;
    private final String className;

    public AbstractService(String className, JpaRepository<T, Long> repository) {
        this.repository = repository;
        this.className = className;
    }

    @Override
    public T findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> {
                    throw new RepositoryException(className + " with id = " + id + " is absent!");
                });
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    public long count() {
        return repository.count();
    }
}
