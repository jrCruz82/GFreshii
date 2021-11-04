package com.edbootcamp.view;

import java.util.List;

import com.edbootcamp.api.entity.RecipeView;

//TODO Delete all the Hibernates stuff,  add JSON annotations as needed for the UI

public class RecipeViewImpl implements RecipeView {

	private Long id;
	private String name;
	private String instruction;	
	
	
	private List<IngredientViewImpl> ingredients;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String recipe) {
		this.name = recipe;
	}
	//needs to be view
	public List<IngredientViewImpl> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<IngredientViewImpl> ingredients) {
		this.ingredients = ingredients;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

    @Override
    public String toString() {
        return "Recipe [id=" + id + ", name=" + name +", instructions"+instruction +"]";
    }


	
}
