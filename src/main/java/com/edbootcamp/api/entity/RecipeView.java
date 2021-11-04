package com.edbootcamp.api.entity;

import java.util.List;

import com.edbootcamp.view.IngredientViewImpl;


public interface RecipeView {

	 Long getId();
	 void setId(Long id);
	 String getName();
	 void setName(String recipe);
	 List<IngredientViewImpl> getIngredients();
	 void setIngredients(List<IngredientViewImpl> ingredients);
	 String getInstruction();
	 void setInstruction(String instruction);
}
