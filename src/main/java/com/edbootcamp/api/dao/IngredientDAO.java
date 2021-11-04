package com.edbootcamp.api.dao;

import java.util.List;

import com.edbootcamp.entity.IngredientImpl;

public interface IngredientDAO {

	List<IngredientImpl> allIngredients(Long id);
	IngredientImpl saveIngredient(Long id, IngredientImpl ingredient);
	void deleteIngredient(Long id,IngredientImpl ingredient);
	IngredientImpl findByName(String name, Long id);
	IngredientImpl updateIngredient(Long id, IngredientImpl impl);
	IngredientImpl findById(Long id);
}
