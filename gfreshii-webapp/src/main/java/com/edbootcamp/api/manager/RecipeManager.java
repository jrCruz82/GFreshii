package com.edbootcamp.api.manager;

import java.util.List;

import com.edbootcamp.api.views.RecipeView;

public interface RecipeManager{
	
	List<RecipeView> allRecipes();
	RecipeView saveRecipe(RecipeView recipe);
	RecipeView updateRecipe(RecipeView currentRecipe);
	void deleteRecipeById(Long id);
	RecipeView addInstruction(Long id, RecipeView recipe);
	void deleteRecipeInstructionById(Long id);
	
}
