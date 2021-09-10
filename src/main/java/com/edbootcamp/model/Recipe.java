package com.edbootcamp.model;

import java.util.List;


public class Recipe {
	
	private Long id;
	private String name;
	private List<Ingredient> ingredients;
	private String instruction;
	
	public Recipe() {}
	
	public Recipe(long id, String recipe, List<Ingredient> listIngredients, String instruction ) {
		this.id = id;
		this.name = recipe;
		this.ingredients = listIngredients;
		this.instruction = instruction;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String recipe) {
		this.name = recipe;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}




	
}
