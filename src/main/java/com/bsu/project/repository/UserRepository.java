package com.bsu.project.repository;

import com.bsu.project.entity.User;
import com.bsu.project.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * @author Gulshirin Berdiyeva
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    Optional<User> findById(Long aLong);

    @Override
    List<User> findAll();

    @Override
    <S extends User> S save(S entity);

    @Override
    void deleteById(Long aLong);

    List<User> findUserByUsername(String username);

    @Query(value = "select count(*) from users where role = 'CLIENT'", nativeQuery = true)
    long countAllClients();

    List<User> findUserByRole(UserRole role);

    @Modifying
    @Query(value = "update users set balance = ?1 where id = ?2", nativeQuery = true)
    void updateUserBalance(BigDecimal balance, Long id);

}
