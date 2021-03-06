package com.edbootcamp.api.service;

import java.util.List;

import com.edbootcamp.api.entity.Recipe;

public interface RecipeService {


	List<Recipe> fetchAllRecipes(Long id);
	Recipe saveRecipe(Recipe recipe, Long id);
	
	Boolean isRecipeExist(Recipe recipe);
	Recipe findById(Long id);
	Recipe updateRecipe(Recipe findIpmlImpl);
	void deleteRecipeById(Long id);
	Recipe getIngredientsById(Recipe recipe);
	Recipe addInstruction(Long id, Recipe impl);
	void deleteRecipeInstructionById(Long id);
	
	
}
