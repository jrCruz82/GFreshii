package com.edbootcamp.service;

import java.util.List;

import com.edbootcamp.model.Recipe;

public interface RecipeService {


	List<Recipe> fetchAllRecipes();
	Recipe saveRecipe(Recipe recipe);
	boolean isRecipeExist(Recipe recipe);
	Recipe findById(long id);
	Recipe updateRecipe(Recipe currentRecipe);
	void deleteRecipeById(Long id);
	
	
}
