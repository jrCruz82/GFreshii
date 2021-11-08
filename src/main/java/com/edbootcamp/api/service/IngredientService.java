package com.edbootcamp.api.service;

import java.util.List;

import com.edbootcamp.api.entity.Ingredient;

public interface IngredientService {

	List<Ingredient> fetchAllIngredientsByRecipe(Long id);
	Ingredient saveIngredient(Long id, Ingredient impl);
	void deleteIngredient(Long id, Ingredient ingredient);
	Ingredient findByName(String name, Long id);
	Ingredient updateIngredient(Long id, Ingredient impl);
	Ingredient findById(Long id);
}
