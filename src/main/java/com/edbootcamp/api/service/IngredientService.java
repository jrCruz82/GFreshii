package com.edbootcamp.api.service;

import java.util.List;

import com.edbootcamp.api.entity.Ingredient;
import com.edbootcamp.entity.IngredientImpl;

public interface IngredientService {

	List<IngredientImpl> fetchAllIngredientsByRecipe(Long id);
	IngredientImpl saveIngredient(Long id, IngredientImpl impl);
	void deleteIngredient(Long id, IngredientImpl ingredient);
	Ingredient findByName(String name, Long id);
	IngredientImpl updateIngredient(Long id, IngredientImpl impl);
	IngredientImpl findById(Long id);
}
