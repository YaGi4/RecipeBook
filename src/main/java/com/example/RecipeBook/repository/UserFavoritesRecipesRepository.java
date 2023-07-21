package com.example.RecipeBook.repository;

import com.example.RecipeBook.entites.UserFavoritesRecipes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserFavoritesRecipesRepository extends JpaRepository<UserFavoritesRecipes, Long> {

    @Query(value = "SELECT * FROM user_favorite_recipes", nativeQuery = true)
    List<UserFavoritesRecipes> findAll();

    @Query(value = "SELECT * FROM user_favorite_recipes WHERE id = :id", nativeQuery = true)
    List<UserFavoritesRecipes> findByID(Long id);

    @Query(value = "INSERT INTO user_favorite_recipes(recipe_id, user_id)" +
            "VALUES (:recipe_id, :user_id)", nativeQuery = true)
    void addUserFavoriteRecipes(@Param("recipe_id") Long recipe_id, @Param("user_id") Long user_id);

    @Query(value = "DELETE FROM user_favorite_recipes WHERE id = :id", nativeQuery = true)
    void deleteByID(Long id);
}
