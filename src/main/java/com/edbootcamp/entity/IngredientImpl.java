package com.edbootcamp.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.edbootcamp.api.entity.Ingredient;
import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "ingredient")
public class IngredientImpl implements Ingredient{
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "INGREDIENT_ID")
	private Long id;
	
	@Size(min = 3, max = 50)
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "AMOUNT")
	private String amount;
	
	@Column(name = "UNIT")
	private String unit;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "recipeID",referencedColumnName = "ID")
	@JsonBackReference
	private RecipeImpl recipe;
	
	public IngredientImpl() {}

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
		
		return recipe;
	}
	
	public void setRecipe(RecipeImpl recipe) {
		this.recipe = recipe;
	}


	@Override
	public String toString() {
		return "Ingredient [id=" + id + ", name=" + name + ", amount=" + amount + ", unit=" + unit 
				+", recipeid= " +recipe+ " ]";
	}







	
	
}
