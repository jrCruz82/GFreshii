package com.edbootcamp.api.manager;

import java.util.List;

import com.edbootcamp.api.entity.RecipeView;

public interface RecipeManager{
	
	List<RecipeView> AllRecipes();
	RecipeView saveRecipe(RecipeView recipe);
	Boolean isRecipeExist(RecipeView recipe);
	RecipeView findById(Long id);
	RecipeView updateRecipe(RecipeView currentRecipe);
	void deleteRecipeById(Long id);
	RecipeView addInstruction(Long id, RecipeView recipe);
	void deleteRecipeInstructionById(Long id);
	
}
