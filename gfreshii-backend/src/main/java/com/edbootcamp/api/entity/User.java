package com.edbootcamp.api.entity;

import java.util.List;

import com.edbootcamp.entity.RecipeImpl;

public interface User {

	public Long getId();
	public void setId(Long id);
	public String getFirstName();
	public void setFirstName(String firstName);
	public String getLastName();
	public void setLastName(String lastName);
	public String getUserName();
	public void setUserName(String userName);
	public String getEmail();
	public void setEmail(String email);
	public List<RecipeImpl> getRecipes();
	public void setRecipes(List<RecipeImpl> recipes);
}
