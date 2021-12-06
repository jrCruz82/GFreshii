package com.edbootcamp.view;

import java.util.List;

import com.edbootcamp.api.views.Recipe;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RecipeImpl implements Recipe {
	@JsonProperty("id")
	private Long id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("instruction")
	private String instruction;	
	@JsonProperty("user")
	private UserImpl user;
	@JsonProperty("ingredients")
	@JsonIgnoreProperties(value= {"ingredients"}, allowSetters = true)
	private List<IngredientImpl> ingredients;

	public RecipeImpl() {}
	
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
	public List<IngredientImpl> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<IngredientImpl> ingredients) {
		this.ingredients = ingredients;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	public UserImpl getUser() {
		return user;
	}

	public void setUser(UserImpl user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "RecipeImpl [id=" + id + ", name=" + name + ", instruction=" + instruction + ", user=" + user
				+ ", ingredients=" + ingredients + "]";
	}


	
}
