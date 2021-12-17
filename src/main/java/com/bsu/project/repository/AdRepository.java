package com.bsu.project.repository;

import com.bsu.project.entity.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * @author Gulshirin Berdiyeva
 */
@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {
    @Override
    Optional<Ad> findById(Long aLong);

    @Override
    List<Ad> findAll();

    @Override
    <S extends Ad> S save(S entity);

    @Override
    long count();
}
