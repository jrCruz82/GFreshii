package com.edbootcamp.api.service;

import java.util.List;
import com.edbootcamp.entity.RecipeImpl;

public interface RecipeService {


	List<RecipeImpl> fetchAllRecipes();
	RecipeImpl saveRecipe(RecipeImpl recipe);
	
	boolean isRecipeExist(RecipeImpl recipe);
	RecipeImpl findById(Long id);
	RecipeImpl updateRecipe(RecipeImpl findIpmlImpl);
	void deleteRecipeById(Long id);
	RecipeImpl getIngredientsById(RecipeImpl recipe);
	RecipeImpl addInstruction(Long id, RecipeImpl impl);
	void deleteRecipeInstructionById(Long id);
	
	
}
