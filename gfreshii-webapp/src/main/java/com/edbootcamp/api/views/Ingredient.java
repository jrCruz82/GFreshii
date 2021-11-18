package com.edbootcamp.api.views;

import com.edbootcamp.view.RecipeImpl;

public interface Ingredient {

	Long getId();
	void setId(Long id);
	String getName();
	void setName(String name);
	String getAmount();
	void setAmount(String amount);
	String getUnit();
	void setUnit(String unit);
	RecipeImpl getRecipe();
	void setRecipe(RecipeImpl recipe);
}





