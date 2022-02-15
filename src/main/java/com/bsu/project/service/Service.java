package com.bsu.project.service;

import java.util.List;

/**
 * @author Gulshirin Berdiyeva
 */
public interface Service<T> {
    T findById(Long id);

    List<T> findAll();

    T save(T entity);

    long count();
}
