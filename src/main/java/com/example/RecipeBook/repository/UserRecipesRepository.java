package com.example.RecipeBook.repository;

import com.example.RecipeBook.entites.UserRecipes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRecipesRepository extends JpaRepository<UserRecipes, Long> {

    @Query(value = "SELECT * FROM user_recipes", nativeQuery = true)
    List<UserRecipes> findAll();

    @Query(value = "SELECT * FROM user_recipes WHERE id = :id", nativeQuery = true)
    List<UserRecipes> findByID(Long id);

    @Query(value = "INSERT INTO user_recipes(recipe_id, user_id)" +
            "VALUES (:recipe_id, :user_id)", nativeQuery = true)
    void addUserRecipeReository(@Param("recipe_id") Long recipe_id, @Param("user_id")Long user_id);

    @Query(value = "DELETE FROM user_recipes WHERE id = :id", nativeQuery = true)
    void deleteByID(Long id);
}
