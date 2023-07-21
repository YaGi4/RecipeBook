package com.example.RecipeBook.entites;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "recipe_ingredients")
public class RecipeIngredients {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "component_id", insertable=false, updatable=false)
    private RecipeComponents component;

    @Column(name = "name_of_ingredient")
    private String nameOfIngredients;

    @Column(name = "quantity_of_ingredient")
    private String quantityOfIngredient;
}
