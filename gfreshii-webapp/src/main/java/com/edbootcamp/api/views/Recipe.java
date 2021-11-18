package com.edbootcamp.api.views;

import java.util.List;

import com.edbootcamp.view.IngredientImpl;



public interface Recipe {

	 Long getId();
	 void setId(Long id);
	 String getName();
	 void setName(String recipe);
	 List<IngredientImpl> getIngredients();
	 void setIngredients(List<IngredientImpl> ingredients);
	 String getInstruction();
	 void setInstruction(String instruction);
}
