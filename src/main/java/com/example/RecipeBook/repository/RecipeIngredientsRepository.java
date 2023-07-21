package com.example.RecipeBook.repository;

import com.example.RecipeBook.entites.RecipeIngredients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeIngredientsRepository extends JpaRepository<RecipeIngredients, Long> {
    @Query(value = "SELECT * FROM recipe_ingredients WHERE id = :id", nativeQuery = true)
    List<RecipeIngredients> findByID(Long id);

    @Query(value = "SELECT * FROM recipe_ingredients", nativeQuery = true)
    List<RecipeIngredients> findAllIngr();

    @Query(value = "INSERT INTO recipe_ingredients (name_of_ingredient, component_id, quantity_of_ingredient)" +
            "VALUES (:name_of_ingredient, :component_id, :quantity_of_ingredient)", nativeQuery = true)
    void addIngredient(@Param("name_of_ingredient") String name_of_ingredient, @Param("component_id") Long component_id,
                       @Param("quantity_of_ingredient") String quantity_of_ingredient);

    @Query(value = "DELETE FROM recipe_ingredients WHERE id = :id", nativeQuery = true)
    void deleteByID(Long id);

}
