package com.edbootcamp.api.dao;

import java.util.List;
import com.edbootcamp.entity.RecipeImpl;

public interface RecipeDAO {

	List<RecipeImpl> allRecipes();
	RecipeImpl saveRecipe(RecipeImpl recipe);
	boolean isRecipeExist(RecipeImpl recipe);
	RecipeImpl findById(Long long1);
	RecipeImpl updateRecipe(RecipeImpl currentRecipe);
	void deleteRecipeById(Long id);
	RecipeImpl getIngredientsById(RecipeImpl recipe);
	RecipeImpl addInstruction(Long id, RecipeImpl impl);
	void deleteRecipeInstructionById(Long id);
	
}
