package com.edbootcamp.api.entity;

import com.edbootcamp.view.RecipeViewImpl;

public interface IngredientView {

	Long getId();
	void setId(Long id);
	String getName();
	void setName(String name);
	String getAmount();
	void setAmount(String amount);
	String getUnit();
	void setUnit(String unit);
	RecipeViewImpl getRecipe();
	void setRecipe(RecipeViewImpl recipe);
}





