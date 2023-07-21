package com.example.RecipeBook.repository;

import com.example.RecipeBook.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>{
    @Query(value = "SELECT * FROM users", nativeQuery = true)
    List<User> findAllFromUserRepository();

    @Query(value = "SELECT * FROM users WHERE \"ID\" = :id", nativeQuery = true)
    List<User> findByID(Long id);

    @Query(value = "INSERT INTO users (user_name, email, password, about_user)" +
            " VALUES (:user_name, :email, :password, :about_user)", nativeQuery = true)
    void addUser(@Param("user_name") String user_name, @Param("email") String email,
                 @Param("password") String password, @Param("about_user") String about_user);

    @Query(value = "DELETE FROM users WHERE \"ID\" = :id", nativeQuery = true)
    void deleteByID(Long id);

    @Query(value = "SELECT * FROM users WHERE user_name = :name", nativeQuery = true)
    List<User> findByName(String name);
}