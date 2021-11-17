package com.edbootcamp.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.edbootcamp.api.entity.Recipe;


//TODO Delete any JSON stuff that's only used for the UI

@Entity
@Table(name = "RECIPE")//RECIPE6t 
public class RecipeImpl implements  Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")	
	private Long id;
	@Size(min = 3,max = 50)
	@Column(name = "NAME", nullable = false)
	private String name;
	@Column(name = "INSTRUCTION")
	private String instruction;	
	//lazy
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "recipe", cascade =CascadeType.ALL, orphanRemoval = false)
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

    @Override
    public String toString() {
        return "Recipe [id=" + id + ", name=" + name + "]";
    }


	
}
