package com.edbootcamp.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.edbootcamp.api.entity.Ingredient;
import com.edbootcamp.api.entity.Recipe;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


//TODO Delete any JSON stuff that's only used for the UI

@Entity
@Table(name = "RECIPE")
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
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "recipe", cascade =CascadeType.ALL, orphanRemoval = false)
	@JsonIgnore
	private List<IngredientImpl> ingredients;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID",referencedColumnName = "ID")
	private UserImpl user;
	
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

	public UserImpl getUser() {
		return user;
	}

	public void setUser(UserImpl user) {
		this.user = user;
	}

//	@Override
//	public String toString() {
//		return "RecipeImpl [id=" + id + ", name=" + name + ", instruction=" + instruction +  ", user=" + user + "]";
//	}


	
}
