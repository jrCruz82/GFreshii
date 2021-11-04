package com.edbootcamp.api.manager;

import java.util.List;
import com.edbootcamp.view.RecipeViewImpl;

public interface RecipeManager{
	
	List<RecipeViewImpl> AllRecipes();
	RecipeViewImpl saveRecipe(RecipeViewImpl recipe);
	boolean isRecipeExist(RecipeViewImpl recipe);
	RecipeViewImpl findById(Long id);
	RecipeViewImpl updateRecipe(RecipeViewImpl currentRecipe);
	void deleteRecipeById(Long id);
	RecipeViewImpl addInstruction(Long id, RecipeViewImpl recipe);
	void deleteRecipeInstructionById(Long id);
	
}
