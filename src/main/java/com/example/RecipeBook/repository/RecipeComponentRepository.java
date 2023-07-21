package com.example.RecipeBook.repository;

import com.example.RecipeBook.entites.RecipeComponents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeComponentRepository extends JpaRepository<RecipeComponents, Long> {

    @Query(value = "SELECT * FROM recipe_components WHERE id = :id", nativeQuery = true )
    List<RecipeComponents> findByID(Long id);

    @Query(value = "SELECT * FROM recipe_components", nativeQuery = true)
    List<RecipeComponents> findAll();

    @Query(value = "INSERT INTO recipe_components (component_name, recipe_id)" +
            "VALUES (:component_name, :recipe_id)", nativeQuery = true)
    void addComponent(@Param("component_name") String component_name, @Param("recipe_id") Long recipe_id);

    @Query(value = "DELETE FROM recipe_components WHERE id = :id", nativeQuery = true)
    void deleteByID(Long id);
}
