package com.example.RecipeBook.repository;

import com.example.RecipeBook.entites.RecipeCookingSteps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeCookingStepsRepository extends JpaRepository<RecipeCookingSteps, Long> {

    @Query(value = "SELECT * FROM recipe_cooking_steps WHERE \"recipe_id\" = :Id", nativeQuery = true)
    List<RecipeCookingSteps> findByRecipeId(Long Id);

    @Query(value = "INSERT INTO recipe_cooking_steps(steps_count, description_of_steps, recipe_id) " +
            "VALUES (:count, :description, :recipe_id)", nativeQuery = true)
    void createIngredient(@Param("count") Integer steps_count, @Param("description") String description, @Param("recipe_id") Long recipe_id);

    @Query(value = "DELETE FROM recipe_cooking_steps WHERE id = :id", nativeQuery = true)
    void deleteByID(Long id);

    @Query(value = "SELECT * FROM recipe_cooking_steps WHERE id = :id", nativeQuery = true)
    List<RecipeCookingSteps> findByID(Long id);
}