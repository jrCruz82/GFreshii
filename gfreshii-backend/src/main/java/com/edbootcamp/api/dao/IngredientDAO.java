package com.edbootcamp.api.dao;

import java.util.List;

import com.edbootcamp.api.entity.Ingredient;

public interface IngredientDAO {

	List<Ingredient> allIngredients(Long recipeId);
	Ingredient saveIngredient(Long id, Ingredient ingredient);
	void deleteIngredient(Long id,Ingredient ingredient);
	Ingredient findByName(String name, Long id);
	Ingredient updateIngredient(Long id, Ingredient impl);
	Ingredient findById(Long id);
}
