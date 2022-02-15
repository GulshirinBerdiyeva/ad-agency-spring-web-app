package com.bsu.project.repository;

import com.bsu.project.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Gulshirin Berdiyeva
 */
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Override
    Optional<Schedule> findById(Long aLong);

    @Override
    List<Schedule> findAll();

    @Override
    <S extends Schedule> S save(S entity);
}
