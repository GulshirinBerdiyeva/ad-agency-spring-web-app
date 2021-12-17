package com.bsu.project.repository;

import com.bsu.project.entity.Order;
import com.bsu.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Gulshirin Berdiyeva
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Override
    Optional<Order> findById(Long aLong);

    @Override
    List<Order> findAll();

    @Override
    <S extends Order> S save(S entity);

    List<Order> findAllByUser(User user);

    @Query(value = "select count(*) from orders where user_id = ?1 and payment = true", nativeQuery = true)
    long countAdsByUserIdAndPayment(Long userId);
}
