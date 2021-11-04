package com.edbootcamp.api.manager;

import java.util.List;

import com.edbootcamp.view.IngredientViewImpl;

public interface IngredientManager {

	List<IngredientViewImpl> allIngredientsByRecipe(Long id);
	IngredientViewImpl saveIngredient(Long id, IngredientViewImpl ingredient);
	void deleteIngredientById(Long id, IngredientViewImpl ingredient);
	IngredientViewImpl findByName(String name, Long id);
	IngredientViewImpl updateIngredientById(Long id, IngredientViewImpl ingredient);
	IngredientViewImpl findById(Long id);
}
