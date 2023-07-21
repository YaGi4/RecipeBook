package com.example.RecipeBook.entites;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "recipe_cooking_steps")
public class RecipeCookingSteps {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", insertable=false, updatable=false)
    private Recipe recipe;

    @Column(name = "steps_count")
    private Integer stepsCount;
    @Column(name = "description_of_steps")
    private String descriptionOfSteps;
}
