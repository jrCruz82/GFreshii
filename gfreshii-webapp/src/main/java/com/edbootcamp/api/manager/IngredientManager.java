package com.edbootcamp.api.manager;

import java.util.List;

import com.edbootcamp.api.views.Ingredient;



public interface IngredientManager {

	List<Ingredient> allIngredientsByRecipe(Long id);
	List<Ingredient> saveIngredient(Long id, Ingredient ingredient);
	void deleteIngredientById(Long id, Ingredient ingredient);
	Ingredient updateIngredientById(Long id, Ingredient ingredient);
}
