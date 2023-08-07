package com.example.RecipeBook.repository;

import com.example.RecipeBook.entites.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {


    @Query(value = "SELECT * FROM recipe", nativeQuery = true)
    List<Recipe> findAll();

    @Query(value = "SELECT * FROM recipe WHERE id = :id", nativeQuery = true)
    Recipe findByID(Long id);

    @Query(value = "SELECT * FROM recipe WHERE id IN (:id)", nativeQuery = true)
    List<Recipe> findByID(List<Long> id);

    @Query(value = "INSERT INTO recipe(recipe_name, cooking_time, for_number_of_people, number_of_likes, number_of_favorites, recipe_description, link_on_recipe_photo, recipe_tags)" +
            "VALUES (:name, :time, :for_number_of_people, :likes, :favorites, :description, :photo_link, :tags)", nativeQuery = true)
    void addRecipe(@Param("name") String name, @Param("time") Integer time, @Param("for_number_of_people") Byte number_of_people,
                   @Param("likes") Integer likes, @Param("favorites") Integer favorites, @Param("description") String description,
                   @Param("photo_link") String photo_link, @Param("tags") String[] tags);

    @Query(value = "DELETE FROM recipe WHERE id = :id", nativeQuery = true)
    void deleteByID(Long id);
}