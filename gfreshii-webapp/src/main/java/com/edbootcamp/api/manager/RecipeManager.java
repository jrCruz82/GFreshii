package com.edbootcamp.api.manager;

import java.util.List;

import com.edbootcamp.api.views.Recipe;


public interface RecipeManager{
	
	List<Recipe> allRecipes(Long id);
	Recipe saveRecipe(Recipe recipe,Long id);
	Recipe updateRecipe(Recipe currentRecipe);
	void deleteRecipeById(Long id);
	Recipe addInstruction(Long id, Recipe recipe);
	void deleteRecipeInstructionById(Long id);
	
}
