package com.example.RecipeBook.repository;

import com.example.RecipeBook.entites.UserLikedRecipes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserLikedRecipesRepository extends JpaRepository<UserLikedRecipes, Long> {

    @Query(value = "SELECT * FROM user_liked_recipes", nativeQuery = true)
    List<UserLikedRecipes> findAll();

    @Query(value = "SELECT * FROM user_liked_recipes WHERE id = :id", nativeQuery = true)
    List<UserLikedRecipes> findByID(Long id);

    @Query(value = "SELECT recipe_id FROM user_liked_recipes WHERE user_id = :id", nativeQuery = true)
    List<Long> findRecipeByUserId(Long id);

    @Query(value = "SELECT id FROM user_liked_recipes WHERE recipe_id = :id", nativeQuery = true)
    List<Long> findIdByRecipeId(Long id);

    @Query(value = "INSERT INTO user_liked_recipes(recipe_id, user_id)" +
            "VALUES (:recipe_id, :user_id)", nativeQuery = true)
    void addUserLikedReository(@Param("recipe_id") Long recipe_id, @Param("user_id")Long user_id);

    @Query(value = "DELETE FROM user_liked_recipes WHERE id = :id", nativeQuery = true)
    void deleteByID(Long id);
}
