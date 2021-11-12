package com.edbootcamp.view;

import com.edbootcamp.api.views.IngredientView;
import com.fasterxml.jackson.annotation.JsonProperty;

public class IngredientViewImpl implements IngredientView{
	@JsonProperty
	private Long id;
	@JsonProperty
	private String name;
	@JsonProperty
	private String amount;
	@JsonProperty
	private String unit;
	@JsonProperty
	private RecipeViewImpl recipeId;
	
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
	
	public RecipeViewImpl getRecipe() {
		return recipeId;
	}
	
	public void setRecipe(RecipeViewImpl recipeId) {
		this.recipeId =  recipeId;
	}

	@Override
	public String toString() {
		return "Ingredient [id=" + id + ", name=" + name + ", amount=" + amount + ", unit=" + unit + ", recipeId="
				+recipeId + "]";
	}
	
}
