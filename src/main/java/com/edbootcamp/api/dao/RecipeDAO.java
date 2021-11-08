package com.edbootcamp.api.dao;

import java.util.List;

import com.edbootcamp.api.entity.Recipe;

public interface RecipeDAO {

	List<Recipe> allRecipes();
	Recipe saveRecipe(Recipe recipe);
	Boolean isRecipeExist(Recipe recipe);
	Recipe findById(Long long1);
	Recipe updateRecipe(Recipe currentRecipe);
	void deleteRecipeById(Long id);
	Recipe getIngredientsById(Recipe recipe);
	Recipe addInstruction(Long id, Recipe impl);
	void deleteRecipeInstructionById(Long id);
	
}
