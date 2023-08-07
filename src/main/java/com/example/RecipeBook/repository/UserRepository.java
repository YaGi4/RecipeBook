package com.example.RecipeBook.repository;

import com.example.RecipeBook.entites.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{
    @Query(value = "SELECT * FROM users", nativeQuery = true)
    Optional <User> findAllFromUserRepository();

    @Query(value = "SELECT * FROM users WHERE \"id\" = :id", nativeQuery = true)
    User findByID(Long id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "INSERT INTO users (user_name, login, password)" +
            " VALUES (:name, :login, :password)", nativeQuery = true)
    void addUser(@Param("name") String name, @Param("login") String login,
                 @Param("password") String password);

    @Query(value = "DELETE FROM users WHERE \"ID\" = :id", nativeQuery = true)
    void deleteByID(Long id);

    @Query(value = "SELECT * FROM users WHERE login = :login", nativeQuery = true)
    Optional<User> findByLogin(String login);
}