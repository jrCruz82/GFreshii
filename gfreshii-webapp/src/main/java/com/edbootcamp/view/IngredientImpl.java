package com.edbootcamp.view;

import com.edbootcamp.api.views.Ingredient;
import com.fasterxml.jackson.annotation.JsonProperty;

public class IngredientImpl implements Ingredient{
	@JsonProperty("id")
	private Long id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("amount")
	private String amount;
	@JsonProperty("unit")
	private String unit;
	@JsonProperty("recipeId")
	private RecipeImpl recipeId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public RecipeImpl getRecipe() {
		return recipeId;
	}
	
	public void setRecipe(RecipeImpl recipeId) {
		this.recipeId =  recipeId;
	}

	@Override
	public String toString() {
		return "Ingredient [id=" + id + ", name=" + name + ", amount=" + amount + ", unit=" + unit + ", recipeId="
				+recipeId + "]";
	}
	
}
