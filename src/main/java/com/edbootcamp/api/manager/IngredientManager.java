package com.edbootcamp.api.manager;

import java.util.List;

import com.edbootcamp.api.views.IngredientView;


public interface IngredientManager {

	List<IngredientView> allIngredientsByRecipe(Long id);
	IngredientView saveIngredient(Long id, IngredientView ingredient);
	void deleteIngredientById(Long id, IngredientView ingredient);
	IngredientView updateIngredientById(Long id, IngredientView ingredient);
}
