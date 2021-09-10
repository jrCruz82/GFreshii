package com.edbootcamp.dao;

import java.util.List;

import com.edbootcamp.model.Recipe;

public interface RecipeDAO {

	List<Recipe> allRecipes();

	Recipe saveRecipe(Recipe recipe);
	
	boolean isRecipeExist(Recipe recipe);

	Recipe findById(long id);

	Recipe updateRecipe(Recipe currentRecipe);

	void deleteRecipeById(Long id);
}
