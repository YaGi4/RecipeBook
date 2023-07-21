package com.example.RecipeBook.entites;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "recipe_components")
public class RecipeComponents {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "component")
    private List<RecipeIngredients> recipeIngredients  = new ArrayList<>();

    @Column(name = "component_name")
    private String ComponentName;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", insertable=false, updatable=false)
    private Recipe recipe;
}
