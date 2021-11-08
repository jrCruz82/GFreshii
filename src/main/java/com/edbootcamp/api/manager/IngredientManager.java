package com.edbootcamp.api.manager;

import java.util.List;

import com.edbootcamp.api.entity.IngredientView;


public interface IngredientManager {

	List<IngredientView> allIngredientsByRecipe(Long id);
	IngredientView saveIngredient(Long id, IngredientView ingredient);
	void deleteIngredientById(Long id, IngredientView ingredient);
	IngredientView findByName(String name, Long id);
	IngredientView updateIngredientById(Long id, IngredientView ingredient);
	IngredientView findById(Long id);
}
