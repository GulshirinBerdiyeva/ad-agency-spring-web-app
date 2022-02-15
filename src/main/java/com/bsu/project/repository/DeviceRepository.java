package com.bsu.project.repository;

import com.bsu.project.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Gulshirin Berdiyeva
 */
@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    @Override
    Optional<Device> findById(Long aLong);

    @Override
    List<Device> findAll();

    @Override
    <S extends Device> S save(S entity);

    @Override
    long count();
}
