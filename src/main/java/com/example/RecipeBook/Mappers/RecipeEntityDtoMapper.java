package com.example.RecipeBook.Mappers;
import com.example.RecipeBook.Dtos.RecipeDto;
import com.example.RecipeBook.entites.Recipe;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RecipeEntityDtoMapper {
    List<RecipeDto> ListModelToListDto (List<Recipe> recipe);
    RecipeDto modelToDto (Recipe recipe);
}
